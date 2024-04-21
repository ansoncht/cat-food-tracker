package server_test

import (
	"github.com/ansoncht/Cat-Food-Helper/internal/db"
	"github.com/ansoncht/Cat-Food-Helper/internal/server"
	"github.com/stretchr/testify/require"
	"testing"
	"time"
)

// TestTrackerServer_Run_Should_Succeed starts the server
// using Run and checks for a successful execution within a timeout.
func TestTrackerServer_Run_Should_Succeed(t *testing.T) {
	t.Parallel()

	client, _ := db.NewMongoClient()

	tester := server.NewTrackerServer(&server.Config{}, client)

	go func() {
		err := tester.Run()
		require.NoError(t, err)
	}()

	<-time.After(2 * time.Second)
}
