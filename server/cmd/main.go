package main

import (
	"fmt"
	"log"
	"os"

	"github.com/ansoncht/Cat-Food-Helper/internal/logger"
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

// nolint
// Print a cool ASCII art header.
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

func newCommand() *cobra.Command {
	logger, _ := logger.GetLogger()

	logger.InfoLogger.Println("in newCommand():: Starting Server...")

	cmd := &cobra.Command{
		Use:     "tracker [args]",
		Short:   shortHelp,
		Long:    longHelp,
		Example: "tracker",
		RunE: func(cmd *cobra.Command, args []string) error {
			trackerServer := server.NewTrackerServer()

			trackerServer.GetFlag(cmd)

			if err := trackerServer.Run(); err != nil {
				logger.ErrorLogger.Fatalf(
					"in newCommand():: Failed to create gRPC server - %v", err)

				return fmt.Errorf("Cobra RunE error: %w", err)
			}

			return nil
		},
	}

	server.SetFlag(cmd)

	return cmd
}

func main() {
	_, err := logger.GetLogger()
	if err != nil {
		log.Fatalf("in main():: Failed to initialize logger - %v", err)
	}

	printCoolHeader()

	cmd := newCommand()
	if err := cmd.Execute(); err != nil {
		log.Fatalf("in main():: Failed to start server - %v", err)
	}

	os.Exit(0)
}
