import 'dart:developer';

import 'package:client/bloc/navigator/app_navigator_bloc.dart';
import 'package:client/bloc/navigator/app_navigator_state.dart';
import 'package:client/pages/auth/auth_page.dart';
import 'package:client/pages/general/loading_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:logging/logging.dart';

// AppNavigator handles navigation of the app
class AppNavigator extends StatelessWidget {
  const AppNavigator({super.key, required this.logger});
  final Logger logger;

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<AppNavigatorBloc, AppNavigatorState>(
      builder: (context, state) {
        return Navigator(
          pages: [
            MaterialPage(child: LoadingPage(logger: logger)),
            if (state.states == AppNavigatorStates.login)
              MaterialPage(child: AuthPage(logger: logger)),
            if (state.states == AppNavigatorStates.login)
              MaterialPage(child: AuthPage(logger: logger)),
          ],
          onPopPage: (route, result) {
            log('Popped Page');
            return route.didPop(result);
          },
          onGenerateRoute: (settings) {
            // Handle dynamic route generation if required
            return MaterialPageRoute(
                builder: (_) => LoadingPage(logger: logger));
          },
        );
      },
    );
  }
}
