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
	tracker.UnimplementedTrackerServer
	config Config
}

// Creates a new TrackerServer.
func NewTrackerServer() *TrackerServer {
	return &TrackerServer{}
}

// Creates a new gRPC server.
func (server *TrackerServer) Run() error {
	logger, _ := logger.GetLogger()

	lis, err := net.Listen("tcp", server.config.GrpcPort)
	if err != nil {
		logger.ErrorLogger.Fatalf("in Run():: Failed to listen - %v", err)
	}

	grpcServer := grpc.NewServer()

	tracker.RegisterTrackerServer(grpcServer, server)

	logger.InfoLogger.Printf("in Run():: Server listening at - %v", lis.Addr())

	reflection.Register(grpcServer)

	if err := grpcServer.Serve(lis); err != nil {
		logger.ErrorLogger.Fatalf("in Run():: Failed to serve gRPC server - %v", err)

		return err
	}

	return nil
}

func (server *TrackerServer) CreateUser(
	_ context.Context,
	request *tracker.CreateUserRequest,
) (*tracker.CreateUserReply, error) {
	logger, _ := logger.GetLogger()
	logger.InfoLogger.Printf(
		"in RegisterUser():: Received new user registration request - "+
			"{username: %s, email: %s}", request.Username, request.Email)

	userID := uuid.New()

	response := &tracker.CreateUserReply{
		UserId: userID.String(),
	}

	return response, nil
}

func (server *TrackerServer) CreateCat(
	_ context.Context,
	request *tracker.CreateCatRequest,
) (*tracker.CreateCatReply, error) {
	logger, _ := logger.GetLogger()
	logger.InfoLogger.Printf(
		"in RegisterUser():: Received new user registration request - "+
			"{name: %s, age: %d, breed: %s}", request.Name, request.Age, request.Breed)

	catID := uuid.New()

	response := &tracker.CreateCatReply{
		CatId: catID.String(),
	}

	return response, nil
}
