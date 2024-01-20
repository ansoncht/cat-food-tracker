package server

import (
	"github.com/ansoncht/Cat-Food-Helper/internal/logger"
	"github.com/spf13/cobra"
)

type Config struct {
	GrpcPort string
	RestPort string
}

func (server *TrackerServer) GetFlag(cmd *cobra.Command) {
	logger, _ := logger.GetLogger()

	grpcPort, err := cmd.Flags().GetString("grpc")
	if err != nil {
		logger.ErrorLogger.Fatalf("in GetFlag():: Failed to get gRPC flag - %v", err)
	}

	restPort, err := cmd.Flags().GetString("rest")
	if err != nil {
		logger.ErrorLogger.Fatalf("in GetFlag():: Failed to get REST flag - %v", err)
	}

	server.config = Config{
		GrpcPort: grpcPort,
		RestPort: restPort,
	}
}

func SetFlag(cmd *cobra.Command) {
	var (
		GrpcPort string
		RestPort string
	)

	cmd.Flags().StringVarP(&GrpcPort,
		"grpc",
		"g",
		":8080",
		"Port listening for gRPC requests",
	)

	cmd.Flags().StringVarP(&RestPort,
		"rest",
		"r",
		":8088",
		"Port listening for REST requests",
	)
}
