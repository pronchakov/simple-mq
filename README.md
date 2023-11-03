## Simple MQ

![Java CI with Maven](https://github.com/pronchakov/simple-mq/actions/workflows/maven.yml/badge.svg)

Current status: Early development

### About

This is a simple implementation of JMS interface. Just for fun :-)

No need to run servers, no network communication.  
Messages can be stored: 
1. In file system  
2. In memory

Default storage: File system

### Storage types

#### File system storage

Queues are folders. Messages are files.  
Default data folder: ./db  
File format: JSON

Use edu.mq.simple.storage.fs.FileSystemStorage class:
``` java
final var connectionFactory = new SimpleMQConnectionFactory();
final var fileSystemStorage = new FileSystemStorage("./db");
final var connection = connectionFactory.createConnection(fileSystemStorage);
...
```

##### JSON message Example

Text message:

```json
{
  "headers": [
    {
      "name": "h1",
      "type": "string",
      "value": "header 1"
    }
  ],
  "type": "text",
  "body": "Message body"
}
```

Bytes message:

```json
{
  "headers": [
    {
      "name": "h2",
      "type": "integer",
      "value": 123
    }
  ],
  "type": "bytes",
  "body": "AQID"
}
```

Map message:

```json
{
  "headers": [],
  "type": "map",
  "body": {
    "m1": {
      "type": "string",
      "value": "qwerty"
    },
    "m2": {
      "type": "integer",
      "value": 123
    }
  }
}
```

#### Memory storage

Messages are objects in memory. Very fast but not persistent.

Use edu.mq.simple.storage.mem.MemoryStorage class:
``` java
final var connectionFactory = new SimpleMQConnectionFactory();
final var memoryStorage = new MemoryStorage();
final var connection = connectionFactory.createConnection(memoryStorage);
...
```

### Purpose

Mainly educational

But it can be used for testing purpose.  
You can prepare messages as JSON in test resources and use SimpleMQ to consume these files as Messages from Queue.

### Working features

##### Header types

* string

##### Message types

* text
* byte
* map

##### Acknowledgement

* Nope

##### Transactions

* Nope

