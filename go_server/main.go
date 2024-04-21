package main

import (
	"fmt"
	"github.com/ansoncht/Cat-Food-Helper/internal/flagger"
	trackerLogger "github.com/ansoncht/Cat-Food-Helper/internal/logger"
	"log"
	"os"

	"github.com/ansoncht/Cat-Food-Helper/internal/db"
	"github.com/ansoncht/Cat-Food-Helper/internal/server"

	"github.com/spf13/cobra"
)

const (
	shortHelp = "Track and log your cat's food preferences and experiences."
	longHelp  = `Cat Food Tracker helps you 
	keep track of your cat's food preferences and experiences. 
	Log details about different cat foods, 
	record your cat's reactions, 
	and maintain a comprehensive history of their meals. 
	Use the command-line interface for easy data entry and 
	ensure your cat enjoys a happy and healthy diet.`
)

// Print a cool ASCII art header.
//
//nolint:lll
func printCoolHeader() {
	log.Print(`


 ____  ____  _____    _____ ____  ____  ____    _____  ____  ____  ____  _  __ _____ ____ 
/   _\/  _ \/__ __\  /    //  _ \/  _ \/  _ \  /__ __\/  __\/  _ \/   _\/ |/ //  __//  __\
|  /  | / \|  / \    |  __\| / \|| / \|| | \|    / \  |  \/|| / \||  /  |   / |  \  |  \/|
|  \__| |-||  | |    | |   | \_/|| \_/|| |_/|    | |  |    /| |-|||  \_ |   \ |  /_ |    /
\____/\_/ \|  \_/    \_/   \____/\____/\____/    \_/  \_/\_\\_/ \|\____/\_|\_\\____\\_/\_\
                                                                                          

                      /^--^\     /^--^\     /^--^\
                      \____/     \____/     \____/
                     /      \   /      \   /      \
                    |        | |        | |        |
                     \__  __/   \__  __/   \__  __/
|^|^|^|^|^|^|^|^|^|^|^|^\ \^|^|^|^/ /^|^|^|^|^\ \^|^|^|^|^|^|^|^|^|^|^|^||^|^|^|^|^|^|^|^|^
| | | | | | | | | | | | |\ \| | |/ /| | | | | | \ \ | | | | | | | | | | | | | | | | | | | |
| | | | | | | | | | | | / / | | |\ \| | | | | |/ /| | | | | | | | | | | | | | | | | | | | |
| | | | | | | | | | | | \/| | | | \/| | | | | |\/ | | | | | | | | | | | | | | | | | | | | |
###########################################################################################
| | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | |
| | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | | |


`)
}

// newCommand creates and returns a configured Cobra command
// for the 'tracker' application.
func newCommand(
	client *db.MongoClient,
	logger trackerLogger.Logger,
) (*cobra.Command, error) {
	logger.InfoLogger.Println("in newCommand():: Starting Server...")

	config := &server.Config{}

	cmd := &cobra.Command{
		Use:     "tracker [args]",
		Short:   shortHelp,
		Long:    longHelp,
		Example: "tracker",
		RunE: func(cmd *cobra.Command, args []string) error {
			trackerServer := server.NewTrackerServer(config, client)
			if err := trackerServer.Run(); err != nil {
				logger.ErrorLogger.Printf(
					"in newCommand():: Failed to create gRPC server - %v", err)

				return fmt.Errorf("in newCommand():: Cobra RunE error: %w", err)
			}

			return nil
		},
	}

	if err := flagger.SetFlags(cmd, config); err != nil {
		logger.WarningLogger.Printf("in newCommand():: Failed to set flags - %v", err)

		return nil, fmt.Errorf("in newCommand():: Failed to set flags: %w", err)
	}

	return cmd, nil
}

// main is the entry point of the Cat Food Tracker application.
// It initializes the logger, sets up the database client,
// creates and configures the Cobra command, and executes the command.
func main() {
	// Prints cli header.
	printCoolHeader()

	// Initializes logger.
	logger, err := trackerLogger.GetLogger()
	if err != nil {
		log.Fatalf("in main():: Failed to initialize logger - %v", err)
	}

	// Initializes database client.
	client, err := db.NewMongoClient()
	if err != nil {
		logger.ErrorLogger.Fatalf(
			"in main():: Failed to create MongoDB client - %v", err)
	}

	// Sets up commander.
	cmd, err := newCommand(client, logger)
	if err != nil {
		logger.ErrorLogger.Printf(
			"in main():: Failed to create cobra command - %v",
			err,
		)
		os.Exit(1)
	}
	// Runs commander.
	if err := cmd.Execute(); err != nil {
		logger.ErrorLogger.Printf("in main():: Failed to start server - %v", err)
		os.Exit(1)
	}

	os.Exit(0)
}
