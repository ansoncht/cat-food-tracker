import 'package:flutter/material.dart';
import 'package:logging/logging.dart';

// LoadingPage is a page to show a loading indicator
class LoadingPage extends StatelessWidget {
  const LoadingPage({super.key, required this.logger});

  final Logger logger;

  @override
  Widget build(BuildContext context) {
    try {
      return Stack(
          alignment: Alignment.center,
          fit: StackFit.expand,
          children: [
            Image.asset(
              'lib/resources/cat-512.png',
              color: const Color(0xFFd9bfb0),
            ),
            Align(
                alignment: Alignment.center,
                child: SizedBox(
                  height: MediaQuery.of(context).size.height * .7,
                  width: MediaQuery.of(context).size.height * .7,
                  child: const CircularProgressIndicator(
                    strokeWidth: 7,
                    color: Color(0xFFd9bfb0),
                  ),
                ))
          ]);
    } on Error catch (e) {
      logger.finest(e.toString());
      rethrow;
    }
  }
}
