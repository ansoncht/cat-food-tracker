package com.ansoncht.catfoodtracker.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The tracking service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: tracker.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TrackerGrpc {

  private TrackerGrpc() {}

  public static final java.lang.String SERVICE_NAME = "tracker.Tracker";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ansoncht.catfoodtracker.proto.CreateUserRequest,
      com.ansoncht.catfoodtracker.proto.CreateUserReply> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUser",
      requestType = com.ansoncht.catfoodtracker.proto.CreateUserRequest.class,
      responseType = com.ansoncht.catfoodtracker.proto.CreateUserReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ansoncht.catfoodtracker.proto.CreateUserRequest,
      com.ansoncht.catfoodtracker.proto.CreateUserReply> getCreateUserMethod() {
    io.grpc.MethodDescriptor<com.ansoncht.catfoodtracker.proto.CreateUserRequest, com.ansoncht.catfoodtracker.proto.CreateUserReply> getCreateUserMethod;
    if ((getCreateUserMethod = TrackerGrpc.getCreateUserMethod) == null) {
      synchronized (TrackerGrpc.class) {
        if ((getCreateUserMethod = TrackerGrpc.getCreateUserMethod) == null) {
          TrackerGrpc.getCreateUserMethod = getCreateUserMethod =
              io.grpc.MethodDescriptor.<com.ansoncht.catfoodtracker.proto.CreateUserRequest, com.ansoncht.catfoodtracker.proto.CreateUserReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ansoncht.catfoodtracker.proto.CreateUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ansoncht.catfoodtracker.proto.CreateUserReply.getDefaultInstance()))
              .setSchemaDescriptor(new TrackerMethodDescriptorSupplier("CreateUser"))
              .build();
        }
      }
    }
    return getCreateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ansoncht.catfoodtracker.proto.LoginUserRequest,
      com.ansoncht.catfoodtracker.proto.LoginUserReply> getLoginUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LoginUser",
      requestType = com.ansoncht.catfoodtracker.proto.LoginUserRequest.class,
      responseType = com.ansoncht.catfoodtracker.proto.LoginUserReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ansoncht.catfoodtracker.proto.LoginUserRequest,
      com.ansoncht.catfoodtracker.proto.LoginUserReply> getLoginUserMethod() {
    io.grpc.MethodDescriptor<com.ansoncht.catfoodtracker.proto.LoginUserRequest, com.ansoncht.catfoodtracker.proto.LoginUserReply> getLoginUserMethod;
    if ((getLoginUserMethod = TrackerGrpc.getLoginUserMethod) == null) {
      synchronized (TrackerGrpc.class) {
        if ((getLoginUserMethod = TrackerGrpc.getLoginUserMethod) == null) {
          TrackerGrpc.getLoginUserMethod = getLoginUserMethod =
              io.grpc.MethodDescriptor.<com.ansoncht.catfoodtracker.proto.LoginUserRequest, com.ansoncht.catfoodtracker.proto.LoginUserReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LoginUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ansoncht.catfoodtracker.proto.LoginUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ansoncht.catfoodtracker.proto.LoginUserReply.getDefaultInstance()))
              .setSchemaDescriptor(new TrackerMethodDescriptorSupplier("LoginUser"))
              .build();
        }
      }
    }
    return getLoginUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ansoncht.catfoodtracker.proto.CreateCatRequest,
      com.ansoncht.catfoodtracker.proto.CreateCatReply> getCreateCatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateCat",
      requestType = com.ansoncht.catfoodtracker.proto.CreateCatRequest.class,
      responseType = com.ansoncht.catfoodtracker.proto.CreateCatReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ansoncht.catfoodtracker.proto.CreateCatRequest,
      com.ansoncht.catfoodtracker.proto.CreateCatReply> getCreateCatMethod() {
    io.grpc.MethodDescriptor<com.ansoncht.catfoodtracker.proto.CreateCatRequest, com.ansoncht.catfoodtracker.proto.CreateCatReply> getCreateCatMethod;
    if ((getCreateCatMethod = TrackerGrpc.getCreateCatMethod) == null) {
      synchronized (TrackerGrpc.class) {
        if ((getCreateCatMethod = TrackerGrpc.getCreateCatMethod) == null) {
          TrackerGrpc.getCreateCatMethod = getCreateCatMethod =
              io.grpc.MethodDescriptor.<com.ansoncht.catfoodtracker.proto.CreateCatRequest, com.ansoncht.catfoodtracker.proto.CreateCatReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateCat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ansoncht.catfoodtracker.proto.CreateCatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ansoncht.catfoodtracker.proto.CreateCatReply.getDefaultInstance()))
              .setSchemaDescriptor(new TrackerMethodDescriptorSupplier("CreateCat"))
              .build();
        }
      }
    }
    return getCreateCatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TrackerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerStub>() {
        @java.lang.Override
        public TrackerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerStub(channel, callOptions);
        }
      };
    return TrackerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TrackerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerBlockingStub>() {
        @java.lang.Override
        public TrackerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerBlockingStub(channel, callOptions);
        }
      };
    return TrackerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TrackerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerFutureStub>() {
        @java.lang.Override
        public TrackerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerFutureStub(channel, callOptions);
        }
      };
    return TrackerFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The tracking service definition.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * ******** User related RPCs ********
     * Create a user.
     * </pre>
     */
    default void createUser(com.ansoncht.catfoodtracker.proto.CreateUserRequest request,
        io.grpc.stub.StreamObserver<com.ansoncht.catfoodtracker.proto.CreateUserReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * Log in a user.
     * </pre>
     */
    default void loginUser(com.ansoncht.catfoodtracker.proto.LoginUserRequest request,
        io.grpc.stub.StreamObserver<com.ansoncht.catfoodtracker.proto.LoginUserReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * ******** Cat related RPCs ********
     * Create a cat.
     * </pre>
     */
    default void createCat(com.ansoncht.catfoodtracker.proto.CreateCatRequest request,
        io.grpc.stub.StreamObserver<com.ansoncht.catfoodtracker.proto.CreateCatReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateCatMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Tracker.
   * <pre>
   * The tracking service definition.
   * </pre>
   */
  public static abstract class TrackerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TrackerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Tracker.
   * <pre>
   * The tracking service definition.
   * </pre>
   */
  public static final class TrackerStub
      extends io.grpc.stub.AbstractAsyncStub<TrackerStub> {
    private TrackerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerStub(channel, callOptions);
    }

    /**
     * <pre>
     * ******** User related RPCs ********
     * Create a user.
     * </pre>
     */
    public void createUser(com.ansoncht.catfoodtracker.proto.CreateUserRequest request,
        io.grpc.stub.StreamObserver<com.ansoncht.catfoodtracker.proto.CreateUserReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Log in a user.
     * </pre>
     */
    public void loginUser(com.ansoncht.catfoodtracker.proto.LoginUserRequest request,
        io.grpc.stub.StreamObserver<com.ansoncht.catfoodtracker.proto.LoginUserReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ******** Cat related RPCs ********
     * Create a cat.
     * </pre>
     */
    public void createCat(com.ansoncht.catfoodtracker.proto.CreateCatRequest request,
        io.grpc.stub.StreamObserver<com.ansoncht.catfoodtracker.proto.CreateCatReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateCatMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Tracker.
   * <pre>
   * The tracking service definition.
   * </pre>
   */
  public static final class TrackerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TrackerBlockingStub> {
    private TrackerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * ******** User related RPCs ********
     * Create a user.
     * </pre>
     */
    public com.ansoncht.catfoodtracker.proto.CreateUserReply createUser(com.ansoncht.catfoodtracker.proto.CreateUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Log in a user.
     * </pre>
     */
    public com.ansoncht.catfoodtracker.proto.LoginUserReply loginUser(com.ansoncht.catfoodtracker.proto.LoginUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ******** Cat related RPCs ********
     * Create a cat.
     * </pre>
     */
    public com.ansoncht.catfoodtracker.proto.CreateCatReply createCat(com.ansoncht.catfoodtracker.proto.CreateCatRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateCatMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Tracker.
   * <pre>
   * The tracking service definition.
   * </pre>
   */
  public static final class TrackerFutureStub
      extends io.grpc.stub.AbstractFutureStub<TrackerFutureStub> {
    private TrackerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * ******** User related RPCs ********
     * Create a user.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ansoncht.catfoodtracker.proto.CreateUserReply> createUser(
        com.ansoncht.catfoodtracker.proto.CreateUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Log in a user.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ansoncht.catfoodtracker.proto.LoginUserReply> loginUser(
        com.ansoncht.catfoodtracker.proto.LoginUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ******** Cat related RPCs ********
     * Create a cat.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ansoncht.catfoodtracker.proto.CreateCatReply> createCat(
        com.ansoncht.catfoodtracker.proto.CreateCatRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateCatMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_USER = 0;
  private static final int METHODID_LOGIN_USER = 1;
  private static final int METHODID_CREATE_CAT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_USER:
          serviceImpl.createUser((com.ansoncht.catfoodtracker.proto.CreateUserRequest) request,
              (io.grpc.stub.StreamObserver<com.ansoncht.catfoodtracker.proto.CreateUserReply>) responseObserver);
          break;
        case METHODID_LOGIN_USER:
          serviceImpl.loginUser((com.ansoncht.catfoodtracker.proto.LoginUserRequest) request,
              (io.grpc.stub.StreamObserver<com.ansoncht.catfoodtracker.proto.LoginUserReply>) responseObserver);
          break;
        case METHODID_CREATE_CAT:
          serviceImpl.createCat((com.ansoncht.catfoodtracker.proto.CreateCatRequest) request,
              (io.grpc.stub.StreamObserver<com.ansoncht.catfoodtracker.proto.CreateCatReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ansoncht.catfoodtracker.proto.CreateUserRequest,
              com.ansoncht.catfoodtracker.proto.CreateUserReply>(
                service, METHODID_CREATE_USER)))
        .addMethod(
          getLoginUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ansoncht.catfoodtracker.proto.LoginUserRequest,
              com.ansoncht.catfoodtracker.proto.LoginUserReply>(
                service, METHODID_LOGIN_USER)))
        .addMethod(
          getCreateCatMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ansoncht.catfoodtracker.proto.CreateCatRequest,
              com.ansoncht.catfoodtracker.proto.CreateCatReply>(
                service, METHODID_CREATE_CAT)))
        .build();
  }

  private static abstract class TrackerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TrackerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ansoncht.catfoodtracker.proto.TrakerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Tracker");
    }
  }

  private static final class TrackerFileDescriptorSupplier
      extends TrackerBaseDescriptorSupplier {
    TrackerFileDescriptorSupplier() {}
  }

  private static final class TrackerMethodDescriptorSupplier
      extends TrackerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TrackerMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TrackerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TrackerFileDescriptorSupplier())
              .addMethod(getCreateUserMethod())
              .addMethod(getLoginUserMethod())
              .addMethod(getCreateCatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
