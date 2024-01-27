package server_test

import (
	"context"
	"github.com/ansoncht/Cat-Food-Helper/internal/db"
	tracker "github.com/ansoncht/Cat-Food-Helper/internal/pb"
	"github.com/ansoncht/Cat-Food-Helper/internal/server"
	"github.com/ansoncht/Cat-Food-Helper/test/mocks"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/require"
	"go.uber.org/mock/gomock"
	"testing"
)

// TestRegisterUserInterface tests the user registration interface
// by using a mock TrackerService.
func TestRegisterUserInterface(t *testing.T) {
	t.Parallel()

	ctx := context.Background()

	ctrl := gomock.NewController(t)

	defer ctrl.Finish()

	tester := mocks.NewMockTrackerService(ctrl)

	request := &tracker.CreateUserRequest{
		Username: "test_username",
		Email:    "test_email",
		Password: "test_password",
	}

	response := &tracker.CreateUserReply{
		UserId: "test_uuid",
	}

	tester.
		EXPECT().
		CreateUser(ctx, request).
		Return(response, nil)

	resp, err := tester.CreateUser(ctx, request)

	assert.NotEmpty(t, resp.UserId)
	require.NoError(t, err)
}

// TestRegisterCatInterface tests the cat registration interface
// by using a mock TrackerService.
func TestRegisterCatInterface(t *testing.T) {
	t.Parallel()

	ctx := context.Background()

	ctrl := gomock.NewController(t)

	defer ctrl.Finish()

	tester := mocks.NewMockTrackerService(ctrl)

	request := &tracker.CreateCatRequest{
		Name:  "test_username",
		Age:   0,
		Breed: "test_breed",
	}

	response := &tracker.CreateCatReply{
		CatId: "test_uuid",
	}

	tester.
		EXPECT().
		CreateCat(ctx, request).
		Return(response, nil)

	resp, err := tester.CreateCat(ctx, request)

	assert.NotEmpty(t, resp.CatId)
	require.NoError(t, err)
}

// TestRegisterUser tests the user registration function in the TrackerServer.
func TestRegisterUser(t *testing.T) {
	t.Parallel()

	client, _ := db.NewMongoClient()

	tester := server.NewTrackerServer(&server.Config{}, client)

	request := &tracker.CreateUserRequest{
		Username: "test_username",
		Email:    "test_email",
		Password: "test_password",
	}

	resp, err := tester.CreateUser(context.Background(), request)

	assert.NotEmpty(t, resp.UserId)
	require.NoError(t, err)
}

// TestRegisterCat tests the cat registration function in the TrackerServer.
func TestRegisterCat(t *testing.T) {
	t.Parallel()

	client, _ := db.NewMongoClient()

	tester := server.NewTrackerServer(&server.Config{}, client)

	request := &tracker.CreateCatRequest{
		Name:  "test_username",
		Age:   0,
		Breed: "test_breed",
	}

	resp, err := tester.CreateCat(context.Background(), request)

	assert.NotEmpty(t, resp.CatId)
	require.NoError(t, err)
}
