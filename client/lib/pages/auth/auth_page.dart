import 'package:client/component/auth/signin_form.dart';
import 'package:client/component/auth/signup_form.dart';
import 'package:flutter/material.dart';
import 'package:logging/logging.dart';

// AuthPage is an authentication page for sign-in / sign-up.
class AuthPage extends StatefulWidget {
  const AuthPage({
    super.key,
    required this.logger,
  });

  final Logger logger;

  @override
  AuthPageState createState() => AuthPageState();
}

// AuthPageState is the state class for AuthPage.
class AuthPageState extends State<AuthPage> {
  bool _isSignIn = true;

  void toggleIsSignInState() {
    setState(() {
      _isSignIn = !_isSignIn;
    });
  }

  @override
  Widget build(BuildContext context) {
    try {
      return Scaffold(
        backgroundColor: Theme.of(context).colorScheme.background,
        body: Center(
          child: Column(
            children: [
              Container(
                margin: const EdgeInsets.only(
                  top: 50,
                  bottom: 25,
                ), // Add margin of 20 pixels above the widget
                child: Image.asset(
                  'lib/resources/cropped_icon_nobackground.png',
                  fit: BoxFit.contain,
                  height: 150,
                  width: 150,
                ),
              ),
              _isSignIn
                  ? Expanded(
                      child: SignInForm(
                        logger: widget.logger,
                        function: toggleIsSignInState,
                      ),
                    )
                  : Expanded(
                      child: SignUpForm(
                        logger: widget.logger,
                        function: toggleIsSignInState,
                      ),
                    ),
            ],
          ),
        ),
      );
    } on Error catch (e) {
      widget.logger.finest(e.toString());
      rethrow;
    }
  }
}
