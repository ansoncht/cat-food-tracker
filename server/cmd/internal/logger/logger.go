package logger

import (
	"errors"
	"fmt"
	"log"
	"os"
	"sync"
)

var (
	// Variables
	customLogger Logger
	loggerError  error
	once         sync.Once

	// Errors
	ErrCreateLogger = errors.New("logger creation failed")
)

type Logger struct {
	WarningLogger *log.Logger
	InfoLogger    *log.Logger
	ErrorLogger   *log.Logger
}

func GetLogger() (Logger, error) {
	once.Do(func() {
		customLogger, loggerError = newLogger()
	})

	if loggerError != nil {
		return Logger{}, fmt.Errorf("in GetLogger():: %w", ErrCreateLogger)
	}

	return customLogger, nil
}

func newLogger() (Logger, error) {
	var logger Logger

	logger.InfoLogger = log.New(os.Stdout, "INFO: ", log.Ldate|log.Ltime|log.Lshortfile)
	if logger.InfoLogger == nil {
		return Logger{}, fmt.Errorf("in newLogger():: InfoLogger - %w", ErrCreateLogger)
	}

	logger.WarningLogger = log.New(os.Stdout, "WARNING: ", log.Ldate|log.Ltime|log.Lshortfile)
	if logger.WarningLogger == nil {
		return Logger{}, fmt.Errorf("in newLogger():: WarningLogger - %w", ErrCreateLogger)
	}

	logger.ErrorLogger = log.New(os.Stdout, "ERROR: ", log.Ldate|log.Ltime|log.Lshortfile)
	if logger.ErrorLogger == nil {
		return Logger{}, fmt.Errorf("in newLogger():: ErrorLogger - %w", ErrCreateLogger)
	}

	return logger, nil
}
