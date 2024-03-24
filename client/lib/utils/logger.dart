import 'dart:developer';
import 'package:logging/logging.dart';

// log level.
const logLevel = Level.FINEST;

// createLogger creates a custom logger.
Logger createLogger(String name) {
  final Logger logger = Logger(name);
  Logger.root.level = logLevel;
  Logger.root.onRecord.listen(printLogRecord);
  return logger;
}

// printLogRecord prints the formatted log to the console.
void printLogRecord(LogRecord r) {
  log("${r.loggerName} (${r.level}) --- ${r.message}");
}
