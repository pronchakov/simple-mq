## Simple MQ

![Java CI with Maven](https://github.com/pronchakov/simple-mq/actions/workflows/maven.yml/badge.svg)

Current status: Early development

### About

This is a simple implementation of JMS interface. Just for fun :-)

No need to run servers, no network communication. Just pure file system implementation.

### Idea

Queues are folders. Messages are files. It's simple.

### Purpose

Mainly educational

But it can be used for testing purpose.  
You can prepare messages as JSON in test resources and use SimpleMQ to consume these files as Messages from Queue.

### Message format

Messages saves into separate files with JSON content.

#### Example

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

