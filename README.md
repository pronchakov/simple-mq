## Simple MQ

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

```json
{
  "headers": {
    "header1": "value1",
    "header2": 12
  },
  "bodyType" : "text",
  "body": "Message body"
}
```

```json
{
  "headers": {
    "header1": "value1",
    "header2": 12
  },
  "bodyType" : "bytes",
  "body": "AQID"
}
```

### Working features
##### Header types
* Nope

##### Message types
* text
* byte

##### Transactions
* Nope

