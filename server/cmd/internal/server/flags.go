package server

import "flag"

var (
	grpcPort string
	restPort string
)

// Set the flags.
func setFlag() {
	flag.StringVar(
		&grpcPort,
		"gRPC Port",
		":8080",
		"Port listening for gRPC requests")

	flag.StringVar(
		&restPort,
		"REST Port",
		":8088",
		"Port listening for REST requests")
}

// Register the flags.
func RegisterFlag() {
	setFlag()

	flag.Parse()
}
