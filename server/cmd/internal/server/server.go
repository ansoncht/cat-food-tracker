package server

import (
	"context"
	"net"

	"github.com/ansoncht/Cat-Food-Helper/internal/logger"
	tracker "github.com/ansoncht/Cat-Food-Helper/internal/pb"
	"github.com/google/uuid"
	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

// TrackerService is an interface for all the RPCs.
type TrackerService interface {
	RegisterUser(
		ctx context.Context,
		user *tracker.UserRequest,
	) (*tracker.UserReply, error)
}

// TrackerServer is a struct for TrackerServer gRPC server.
type TrackerServer struct {
	tracker.UnimplementedTrackerServer
}

// Creates a new TrackerServer.
func NewTrackerServer() *TrackerServer {
	return &TrackerServer{}
}

// Creates a new gRPC server.
func (server *TrackerServer) Run(ctx context.Context) error {
	logger, _ := logger.GetLogger()

	lis, err := net.Listen("tcp", grpcPort)
	if err != nil {
		logger.ErrorLogger.Fatalf("in Run():: Failed to listen %v", err)
	}

	s := grpc.NewServer()

	tracker.RegisterTrackerServer(s, server)

	logger.InfoLogger.Printf("in Run():: Server listening at %v", lis.Addr())

	reflection.Register(s)

	return s.Serve(lis)
}

func (server *TrackerServer) RegisterUser(ctx context.Context, request *tracker.UserRequest) (*tracker.UserReply, error) {
	logger, _ := logger.GetLogger()
	logger.InfoLogger.Printf("in RegisterUser():: Received new user registration request - {username: %s, email: %s}", request.Username, request.Email)

	userID := uuid.New()

	response := &tracker.UserReply{
		Username: request.Username,
		Uuid:     userID.String(),
	}

	return response, nil
}
