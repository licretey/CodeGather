+ spring生命周期
+ sping状态机
+ springEvent
+ spring事务传递

| 属性值                     | 行为                                     |
| -------------------------- | ---------------------------------------- |
| REQUIRED(必须有)           | 外层有，内层加入；外层没有，内层新建     |
| REQUIRED_NEW(必须有新事务) | 外层有，内层新建；外层没有，内层也新建   |
| SUPPORTS(支持有)           | 外层有，内层加入；外层没有，内层没有     |
| NO_SUPPORTED(支持没有)     | 外层有，内层没有；外层没有，内层没有     |
| MANDATORY(强制要求外层有)  | 外层有，内层加入；外层没有，内层**报错** |
| NEVER(不允许有)            | 外层有，**内层报错**；外层没有，内层没有 |

+ spring隔离级别
    + DEFAULT
    + READ_COMMITED
    + READ_UNCOMMITED
    + REPEATEABLE_READ
    + SERIALIZABLE



+ spring webflux

