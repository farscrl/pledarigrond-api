# Pronunciation module

## State diagram

A registration can have the following states: 

```mermaid
stateDiagram-v2
    [*] --> TODO

    TODO --> IN_REVIEW
    TODO --> POSTPONED
    POSTPONED --> IN_REVIEW
    IN_REVIEW --> COMPLETED
    IN_REVIEW --> REFUSED
    IN_REVIEW --> POSTPONED_REVIEW
    POSTPONED_REVIEW --> COMPLETED
    POSTPONED_REVIEW --> REFUSED
    REFUSED --> IN_REVIEW
```


## Dependencies

This module depends on JAVE (Java Audio Video Encoder) to convert audio files. Currently, the pom includes the binaries for MacOS M1 and Linux 32/64 bit.
If other platforms have to be supported, the pom.xml file has to be updated accordingly.
