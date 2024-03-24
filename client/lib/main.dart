import 'package:client/bloc/navigator/app_navigator_bloc.dart';
import 'package:client/bloc/navigator/app_navigator_event.dart';
import 'package:client/navigator/app_navigator.dart';
import 'package:client/utils/logger.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:logging/logging.dart';

void main() {
  Logger logger = createLogger('TrackerLogger');

  runApp(CatFoodTracker(
    logger: logger,
  ));
}

class CatFoodTracker extends StatelessWidget {
  const CatFoodTracker({super.key, required this.logger});

  final Logger logger;

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [
        BlocProvider<AppNavigatorBloc>(
            create: (BuildContext context) =>
                AppNavigatorBloc(logger)..add(AppNavigatorEventClickLogin()))
      ],
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'MeowAI',
        theme: ThemeData(
          textTheme: GoogleFonts.kanitTextTheme(),
          colorScheme: const ColorScheme(
            brightness: Brightness.light,
            primary: Color(0xFF88a0bf),
            onPrimary: Color(0xFFaaffff),
            secondary: Color(0xFFd9bfb0),
            onSecondary: Color(0xFFffffff),
            error: Color(0xFFa67360),
            onError: Color(0xFFffffff),
            background: Color(0xFFf2e7dc),
            onBackground: Color(0xFF0d1823),
            surface: Color(0xFF593b34),
            onSurface: Color(0xFFffffff),
          ),
          useMaterial3: true,
        ),
        home: AppNavigator(logger: logger),
      ),
    );
  }
}
