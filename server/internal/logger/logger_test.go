package trackerlogger_test

import (
	"testing"

	"github.com/ansoncht/Cat-Food-Helper/internal/logger"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/require"
)

// TestGetLogger validates the GetLogger function,
// ensuring a non-nil logger and no errors.
func TestGetLogger(t *testing.T) {
	t.Parallel()

	logger, err := trackerlogger.GetLogger()

	assert.NotNil(t, logger)
	require.NoError(t, err)
}
