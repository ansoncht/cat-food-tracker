import 'package:flutter/material.dart';

// AppNavigatorStates is an enumeration representing
// different states in the app navigation.
enum AppNavigatorStates {
  loading,
  home,
  login,
  explore,
}

// AppNavigatorState holds the AppNavigatorBloc's bloc state.
@immutable
class AppNavigatorState {
  final AppNavigatorStates states;

  const AppNavigatorState({required this.states});

  // loading page loaded state.
  AppNavigatorState loadingPageLoaded() => copyWith(
        states: AppNavigatorStates.loading,
      );

  // home page loaded state.
  AppNavigatorState homePageLoaded() => copyWith(
        states: AppNavigatorStates.home,
      );

  // login page loaded state.
  AppNavigatorState loginPageLoaded() => copyWith(
        states: AppNavigatorStates.login,
      );

  // explore page loaded state.
  AppNavigatorState explorePageLoaded() => copyWith(
        states: AppNavigatorStates.explore,
      );

  // CopyWith method for creating modified instances.
  AppNavigatorState copyWith({
    AppNavigatorStates? states,
  }) {
    return AppNavigatorState(
      states: states ?? this.states,
    );
  }
}
