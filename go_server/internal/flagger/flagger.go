package flagger

import (
	"fmt"
	trackerLogger "github.com/ansoncht/Cat-Food-Helper/internal/logger"
	"github.com/ansoncht/Cat-Food-Helper/internal/server"
	"github.com/spf13/cobra"
)

func SetFlags(cmd *cobra.Command, config *server.Config) error {
	logger, err := trackerLogger.GetLogger()
	if err != nil {
		return fmt.Errorf("in SetFlags():: Failed to get logger - %v", err)
	}

	logger.InfoLogger.Println("in SetFlags():: Setting Flags...")

	cmd.Flags().StringVarP(&config.GrpcPort,
		"grpc",
		"g",
		":8080",
		"Port listening for gRPC requests",
	)

	cmd.Flags().StringVarP(&config.RestPort,
		"rest",
		"r",
		":8088",
		"Port listening for REST requests",
	)

	return nil
}
