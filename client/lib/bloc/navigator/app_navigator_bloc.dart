import 'package:bloc/bloc.dart';
import 'package:logging/logging.dart';

import 'package:client/bloc/navigator/app_navigator_event.dart';
import 'package:client/bloc/navigator/app_navigator_state.dart';

// AppNavigatorBloc handles all the logic when received
// a AppNavigatorEvent and produce a AppNavigatorState.
class AppNavigatorBloc extends Bloc<AppNavigatorEvent, AppNavigatorState> {
  final Logger logger;

  AppNavigatorBloc(this.logger)
      : super(const AppNavigatorState(states: AppNavigatorStates.loading)) {
    on<AppNavigatorEventClickHome>(_appNavigatorEnteredHome);
    on<AppNavigatorEventClickLogin>(_appNavigatorEnteredLogin);
    on<AppNavigatorEventClickExplore>(_appNavigatorEnteredExplore);
  }

  // _appNavigatorEnteredHome emit a new state to the UI
  // that redirects user to the home page.
  Future<void> _appNavigatorEnteredHome(
      AppNavigatorEventClickHome event, Emitter<AppNavigatorState> emit) async {
    try {
      // fake the app startup fetching process.
      await Future.delayed(const Duration(seconds: 1), () {});

      logger.fine('Entering _appNavigatorEnteredHome');

      emit(state.homePageLoaded());
    } on Error catch (e) {
      logger.severe(e.toString());

      emit(state.loadingPageLoaded());

      rethrow;
    }
  }

  // _appNavigatorEnteredLogin emits a new state to the UI
  // that redirects user to the login page.
  Future<void> _appNavigatorEnteredLogin(AppNavigatorEventClickLogin event,
      Emitter<AppNavigatorState> emit) async {
    try {
      // fake the app startup fetching process
      await Future.delayed(const Duration(milliseconds: 250), () {});

      logger.fine('Entering _appNavigatorEnteredLogin');

      emit(state.loginPageLoaded());
    } on Error catch (e) {
      logger.severe(e.toString());

      emit(state.loadingPageLoaded());

      rethrow;
    }
  }

  // _appNavigatorEnteredExplore emit a new state to the UI
  // that redirects user to the explore page.
  Future<void> _appNavigatorEnteredExplore(AppNavigatorEventClickExplore event,
      Emitter<AppNavigatorState> emit) async {
    try {
      // fake the app startup fetching process
      await Future.delayed(const Duration(seconds: 1), () {});

      logger.fine('Entering _appNavigatorEnteredExplore');

      emit(state.explorePageLoaded());
    } on Error catch (e) {
      logger.severe(e.toString());

      emit(state.loadingPageLoaded());

      rethrow;
    }
  }
}
