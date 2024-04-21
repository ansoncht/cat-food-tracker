package db_test

import (
	"github.com/ansoncht/Cat-Food-Helper/internal/db"
	"github.com/stretchr/testify/assert"
	"os"
	"testing"
)

// getTestURI retrieves the MongoDB URI
// from the environment variable MONGODB_TEST_URI.
func getTestURI() string {
	// Replace with your logic to retrieve the test URI
	// from an environment variable or any other configuration source.
	testURI := os.Getenv("MONGODB_TEST_URI")
	return testURI
}

// TestNewMongoClient tests the NewMongoClient function.
func TestNewMongoClient(t *testing.T) {
	testURI := getTestURI()

	// Skip the test if the URI is not provided.
	if testURI == "" {
		t.Skip("Skipping test: MONGODB_TEST_URI is not provided.")
	}

	client, err := db.NewMongoClient()

	assert.NoError(t, err)
	assert.NotNil(t, client)
}

// TestGetClient tests the GetClient function.
func TestGetClient(t *testing.T) {
	testURI := getTestURI()

	// Skip the test if the URI is not provided.
	if testURI == "" {
		t.Skip("Skipping test: MONGODB_TEST_URI is not provided.")
	}

	client, err := db.NewMongoClient()
	assert.NoError(t, err)

	underlyingClient := client.GetClient()
	assert.NotNil(t, underlyingClient)
}
