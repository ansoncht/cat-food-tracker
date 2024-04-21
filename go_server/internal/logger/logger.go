package trackerlogger

import (
	"errors"
	"fmt"
	"log"
	"os"
	"sync"
)

var (
	ErrCreateLogger = errors.New("logger creation failed")
)

// Logger represents a set of loggers for different log levels.
type Logger struct {
	WarningLogger *log.Logger
	InfoLogger    *log.Logger
	ErrorLogger   *log.Logger
}

// GetLogger returns a singleton instance of the Logger.
func GetLogger() (Logger, error) {
	var (
		customLogger Logger
		errLogger    error
		once         sync.Once
	)

	once.Do(func() {
		customLogger, errLogger = newLogger()
	})

	if errLogger != nil {
		return Logger{}, fmt.Errorf("in GetLogger():: %w", ErrCreateLogger)
	}

	return customLogger, nil
}

// newLogger creates a new Logger instance with three loggers for
// different log levels.
func newLogger() (Logger, error) {
	var logger Logger

	logger.InfoLogger = log.New(os.Stdout, "INFO: ",
		log.Ldate|log.Ltime|log.Lshortfile)
	if logger.InfoLogger == nil {
		return Logger{},
			fmt.Errorf("in newLogger():: InfoLogger - %w", ErrCreateLogger)
	}

	logger.WarningLogger = log.New(os.Stdout, "WARNING: ",
		log.Ldate|log.Ltime|log.Lshortfile)
	if logger.WarningLogger == nil {
		return Logger{},
			fmt.Errorf("in newLogger():: WarningLogger - %w", ErrCreateLogger)
	}

	logger.ErrorLogger = log.New(os.Stdout, "ERROR: ",
		log.Ldate|log.Ltime|log.Lshortfile)
	if logger.ErrorLogger == nil {
		return Logger{},
			fmt.Errorf("in newLogger():: ErrorLogger - %w", ErrCreateLogger)
	}

	return logger, nil
}
