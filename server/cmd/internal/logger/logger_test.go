package logger_test

import (
	"testing"

	"github.com/ansoncht/Cat-Food-Helper/internal/logger"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/require"
)

func TestGetLogger(t *testing.T) {
	t.Parallel()

	logger, err := logger.GetLogger()

	assert.NotNil(t, logger)
	require.NoError(t, err)
}
