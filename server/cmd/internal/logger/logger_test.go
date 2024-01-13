package logger

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestGetLogger(t *testing.T) {
	logger, err := GetLogger()

	assert.NotNil(t, logger)
	assert.NoError(t, err)
}
