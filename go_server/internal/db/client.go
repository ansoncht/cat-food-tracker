package db

import (
	"context"
	"fmt"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"time"
)

// MongoClient represents the MongoDB client.
type MongoClient struct {
	client *mongo.Client
}

// NewMongoClient creates a new instance of the MongoDB client.
func NewMongoClient() (*MongoClient, error) {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	var uri = ""

	client, err := mongo.Connect(
		ctx,
		options.Client().ApplyURI(
			uri,
		),
	)
	if err != nil {
		return nil, fmt.Errorf(
			"in NewMongoClient():: Failed to create MongoDB client with URI %s: %v",
			uri,
			err,
		)
	}

	defer func() {
		if err = client.Disconnect(ctx); err != nil {
			panic(err)
		}
	}()

	return &MongoClient{client: client}, nil
}

// GetClient returns the underlying MongoDB client.
func (mc *MongoClient) GetClient() *mongo.Client {
	return mc.client
}
