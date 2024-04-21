package server

import (
	"fmt"
	trackerLogger "github.com/ansoncht/Cat-Food-Helper/internal/logger"
	tracker "github.com/ansoncht/Cat-Food-Helper/internal/pb"
	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
	"net"
)

// Run starts a new gRPC server.
func (server *TrackerServer) Run() error {
	logger, err := trackerLogger.GetLogger()
	if err != nil {
		return fmt.Errorf("in Run():: Failed to get logger - %v", err)
	}

	lis, err := net.Listen("tcp", server.config.GrpcPort)
	if err != nil {
		logger.ErrorLogger.Printf("in Run():: Failed to listen - %v", err)
		return err
	}

	grpcServer := grpc.NewServer()

	tracker.RegisterTrackerServer(grpcServer, server)

	logger.InfoLogger.Printf("in Run():: Server listening at - %v", lis.Addr())

	reflection.Register(grpcServer)

	if err := grpcServer.Serve(lis); err != nil {
		logger.ErrorLogger.Printf("in Run():: Failed to serve gRPC server - %v", err)
		return err
	}

	return nil
}
