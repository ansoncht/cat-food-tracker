package server

import (
	"context"
	"testing"

	tracker "github.com/ansoncht/Cat-Food-Helper/internal/pb"
	mock_server "github.com/ansoncht/Cat-Food-Helper/test/mocks"
	"github.com/stretchr/testify/assert"
	"go.uber.org/mock/gomock"
)

func TestRegisterUser(t *testing.T) {
	ctx := context.Background()

	ctrl := gomock.NewController(t)

	tester := mock_server.NewMockTrackerService(ctrl)

	request := &tracker.UserRequest{
		Username: "test_username",
		Email:    "test_email",
		Password: "test_password",
	}

	response := &tracker.UserReply{
		Username: "test_username",
		Uuid:     "test_uuid",
	}

	tester.
		EXPECT().
		RegisterUser(ctx, request).
		Return(response, nil)

	resp, err := tester.RegisterUser(ctx, request)

	assert.Equal(t, response.Username, resp.Username)
	assert.NoError(t, err)
}
