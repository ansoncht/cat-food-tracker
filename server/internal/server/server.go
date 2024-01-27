package server

import (
	"context"
	"fmt"
	"github.com/ansoncht/Cat-Food-Helper/internal/db"
	trackerLogger "github.com/ansoncht/Cat-Food-Helper/internal/logger"
	tracker "github.com/ansoncht/Cat-Food-Helper/internal/pb"
	"github.com/google/uuid"
)

// TrackerService is an interface for all the RPCs.
type TrackerService interface {
	CreateUser(
		ctx context.Context,
		user *tracker.CreateUserRequest,
	) (*tracker.CreateUserReply, error)
	CreateCat(
		ctx context.Context,
		user *tracker.CreateCatRequest,
	) (*tracker.CreateCatReply, error)
}

// TrackerServer is a struct for TrackerServer gRPC server.
type TrackerServer struct {
	config      *Config
	mongoClient *db.MongoClient
	tracker.UnimplementedTrackerServer
}

type Config struct {
	GrpcPort string
	RestPort string
}

// NewTrackerServer creates a new TrackerServer.
func NewTrackerServer(config *Config, client *db.MongoClient) *TrackerServer {
	return &TrackerServer{
		mongoClient: client,
		config:      config,
	}
}

// CreateUser handles CreateUser rpc.
func (server *TrackerServer) CreateUser(
	_ context.Context,
	request *tracker.CreateUserRequest,
) (*tracker.CreateUserReply, error) {
	logger, err := trackerLogger.GetLogger()
	if err != nil {
		return nil, fmt.Errorf("in CreateUser():: Failed to get logger - %v", err)
	}

	logger.InfoLogger.Printf(
		"in RegisterUser():: Received new user registration request - "+
			"{username: %s, email: %s}", request.Username, request.Email)

	userID := uuid.New()

	response := &tracker.CreateUserReply{
		UserId: userID.String(),
	}

	return response, nil
}

// CreateCat handles CreateCat rpc.
func (server *TrackerServer) CreateCat(
	_ context.Context,
	request *tracker.CreateCatRequest,
) (*tracker.CreateCatReply, error) {
	logger, err := trackerLogger.GetLogger()
	if err != nil {
		return nil, fmt.Errorf("in CreateCat():: Failed to get logger - %v", err)
	}

	logger.InfoLogger.Printf(
		"in RegisterUser():: Received new user registration request - "+
			"{name: %s, age: %d, breed: %s}", request.Name, request.Age, request.Breed)

	catID := uuid.New()

	response := &tracker.CreateCatReply{
		CatId: catID.String(),
	}

	return response, nil
}
