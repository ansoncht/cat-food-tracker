import 'package:flutter/material.dart';

// AppNavigatorEvent holds all the event AppNavigatorBloc can handle.
@immutable
abstract class AppNavigatorEvent {}

// AppNavigatorEventClickHome means user clicked "Home" page.
class AppNavigatorEventClickHome implements AppNavigatorEvent {}

// AppNavigatorEventClickLogin means user clicked "Login" page.
class AppNavigatorEventClickLogin implements AppNavigatorEvent {}

// AppNavigatorEventClickExplore means user clicked "Explore" page.
class AppNavigatorEventClickExplore implements AppNavigatorEvent {}
