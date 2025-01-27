#### 安装步骤

大体安装步骤参考这篇[博文]([CentOS7安装RabbitMQ - 不该相遇在秋天 - 博客园 (cnblogs.com)](https://www.cnblogs.com/fengyumeng/p/11133924.html))

其中第二步， 安装rabbitMQ的步骤安装这篇[博文]([(8条消息) CentOS7.x 安装RabbitMQ后-自定义配置文件_lihongtai的专栏-CSDN博客](https://blog.csdn.net/lihongtai/article/details/88080516))

安装完成后需要在 安装目录/etc/rabbitmq 目录下创建配置文件， 每个操作系统上的都不一样， centos上rabbitmq配置文件的命名为`rabbitmq.conf`, 完成后需要在文件中写入初始的默认配置， 后续可以在这个基础上改动：

```properties
# ======================================
# RabbitMQ broker section
# ======================================

## Related doc guide: http://rabbitmq.com/configure.html. See
## http://rabbitmq.com/documentation.html for documentation ToC.

## Networking
## ====================
##
## Related doc guide: http://rabbitmq.com/networking.html.
##
## By default, RabbitMQ will listen on all interfaces, using
## the standard (reserved) AMQP 0-9-1 and 1.0 port.
##
# listeners.tcp.default = 5672


## To listen on a specific interface, provide an IP address with port.
## For example, to listen only on localhost for both IPv4 and IPv6:
##
# IPv4
# listeners.tcp.local    = 127.0.0.1:5672
# IPv6
# listeners.tcp.local_v6 = ::1:5672

## You can define multiple listeners using listener names
# listeners.tcp.other_port = 5673
# listeners.tcp.other_ip   = 10.10.10.10:5672


## TLS listeners are configured in the same fashion as TCP listeners,
## including the option to control the choice of interface.
##
# listeners.ssl.default = 5671

## Number of Erlang processes that will accept connections for the TCP
## and TLS listeners.
##
# num_acceptors.tcp = 10
# num_acceptors.ssl = 1


## Maximum time for AMQP 0-8/0-9/0-9-1 handshake (after socket connection
## and TLS handshake), in milliseconds.
##
# handshake_timeout = 10000

## Set to 'true' to perform reverse DNS lookups when accepting a
## connection. Hostnames will then be shown instead of IP addresses
## in rabbitmqctl and the management plugin.
##
# reverse_dns_lookups = true

##
## Security, Access Control
## ==============
##

## Related doc guide: http://rabbitmq.com/access-control.html.

## The default "guest" user is only permitted to access the server
## via a loopback interface (e.g. localhost).
## {loopback_users, [<<"guest">>]},
##
# loopback_users.guest = true

## Uncomment the following line if you want to allow access to the
## guest user from anywhere on the network.
# loopback_users.guest = false

## TLS configuration.
##
## Related doc guide: http://rabbitmq.com/ssl.html.
##
# ssl_options.verify               = verify_peer
# ssl_options.fail_if_no_peer_cert = false
# ssl_options.cacertfile           = /path/to/cacert.pem
# ssl_options.certfile             = /path/to/cert.pem
# ssl_options.keyfile              = /path/to/key.pem

## Select an authentication/authorisation backend to use.
##
## Alternative backends are provided by plugins, such as rabbitmq-auth-backend-ldap.
##
## NB: These settings require certain plugins to be enabled.
##
## Related doc guides:
##
##  * http://rabbitmq.com/plugins.html
##  * http://rabbitmq.com/access-control.html
##

# auth_backends.1   = rabbit_auth_backend_internal

## uses separate backends for authentication and authorisation,
## see below.
# auth_backends.1.authn = rabbit_auth_backend_ldap
# auth_backends.1.authz = rabbit_auth_backend_internal

## The rabbitmq_auth_backend_ldap plugin allows the broker to
## perform authentication and authorisation by deferring to an
## external LDAP server.
##
## Relevant doc guides:
##
## * http://rabbitmq.com/ldap.html
## * http://rabbitmq.com/access-control.html
##
## uses LDAP for both authentication and authorisation
# auth_backends.1 = rabbit_auth_backend_ldap

## uses HTTP service for both authentication and
## authorisation
# auth_backends.1 = rabbit_auth_backend_http

## uses two backends in a chain: HTTP first, then internal
# auth_backends.1   = rabbit_auth_backend_http
# auth_backends.2   = rabbit_auth_backend_internal

## Authentication
## The built-in mechanisms are 'PLAIN',
## 'AMQPLAIN', and 'EXTERNAL' Additional mechanisms can be added via
## plugins.
##
## Related doc guide: http://rabbitmq.com/authentication.html.
##
# auth_mechanisms.1 = PLAIN
# auth_mechanisms.2 = AMQPLAIN

## The rabbitmq-auth-mechanism-ssl plugin makes it possible to
## authenticate a user based on the client's x509 (TLS) certificate.
## Related doc guide: http://rabbitmq.com/authentication.html.
##
## To use auth-mechanism-ssl, the EXTERNAL mechanism should
## be enabled:
##
# auth_mechanisms.1 = PLAIN
# auth_mechanisms.2 = AMQPLAIN
# auth_mechanisms.3 = EXTERNAL

## To force x509 certificate-based authentication on all clients,
## exclude all other mechanisms (note: this will disable password-based
## authentication even for the management UI!):
##
# auth_mechanisms.1 = EXTERNAL

## This pertains to both the rabbitmq-auth-mechanism-ssl plugin and
## STOMP ssl_cert_login configurations. See the RabbitMQ STOMP plugin
## configuration section later in this file and the README in
## https://github.com/rabbitmq/rabbitmq-auth-mechanism-ssl for further
## details.
##
## To use the TLS cert's CN instead of its DN as the username
##
# ssl_cert_login_from   = common_name

## TLS handshake timeout, in milliseconds.
##
# ssl_handshake_timeout = 5000


## Password hashing implementation. Will only affect newly
## created users. To recalculate hash for an existing user
## it's necessary to update her password.
##
## To use SHA-512, set to rabbit_password_hashing_sha512.
##
# password_hashing_module = rabbit_password_hashing_sha256

## When importing definitions exported from versions earlier
## than 3.6.0, it is possible to go back to MD5 (only do this
## as a temporary measure!) by setting this to rabbit_password_hashing_md5.
##
# password_hashing_module = rabbit_password_hashing_md5

##
## Default User / VHost
## ====================
##

## On first start RabbitMQ will create a vhost and a user. These
## config items control what gets created.
## Relevant doc guide: http://rabbitmq.com/access-control.html
##
# default_vhost = /
# default_user = guest
# default_pass = guest

# default_permissions.configure = .*
# default_permissions.read = .*
# default_permissions.write = .*

## Tags for default user
##
## For more details about tags, see the documentation for the
## Management Plugin at http://rabbitmq.com/management.html.
##
# default_user_tags.administrator = true

## Define other tags like this:
# default_user_tags.management = true
# default_user_tags.custom_tag = true

##
## Additional network and protocol related configuration
## =====================================================
##

## Set the default AMQP 0-9-1 heartbeat interval (in seconds).
## Related doc guides:
##
## * http://rabbitmq.com/heartbeats.html
## * http://rabbitmq.com/networking.html
##
# heartbeat = 60

## Set the max permissible size of an AMQP frame (in bytes).
##
# frame_max = 131072

## Set the max frame size the server will accept before connection
## tuning occurs
##
# initial_frame_max = 4096

## Set the max permissible number of channels per connection.
## 0 means "no limit".
##
# channel_max = 128

## Customising TCP Listener (Socket) Configuration.
##
## Related doc guides:
##
## * http://rabbitmq.com/networking.html
## * http://www.erlang.org/doc/man/inet.html#setopts-2
##

# tcp_listen_options.backlog = 128
# tcp_listen_options.nodelay = true
# tcp_listen_options.exit_on_close = false
#
# tcp_listen_options.keepalive = true
# tcp_listen_options.send_timeout = 15000
#
# tcp_listen_options.buffer = 196608
# tcp_listen_options.sndbuf = 196608
# tcp_listen_options.recbuf = 196608

##
## Resource Limits & Flow Control
## ==============================
##
## Related doc guide: http://rabbitmq.com/memory.html.

## Memory-based Flow Control threshold.
##
# vm_memory_high_watermark.relative = 0.4

## Alternatively, we can set a limit (in bytes) of RAM used by the node.
##
# vm_memory_high_watermark.absolute = 1073741824

## Or you can set absolute value using memory units (with RabbitMQ 3.6.0+).
## Absolute watermark will be ignored if relative is defined!
##
# vm_memory_high_watermark.absolute = 2GB
##
## Supported units suffixes:
##
## kb, KB: kibibytes (2^10 bytes)
## mb, MB: mebibytes (2^20)
## gb, GB: gibibytes (2^30)

## Fraction of the high watermark limit at which queues start to
## page message out to disc in order to free up memory.
## For example, when vm_memory_high_watermark is set to 0.4 and this value is set to 0.5,
## paging can begin as early as when 20% of total available RAM is used by the node.
##
## Values greater than 1.0 can be dangerous and should be used carefully.
##
## One alternative to this is to use durable queues and publish messages
## as persistent (delivery mode = 2). With this combination queues will
## move messages to disk much more rapidly.
##
## Another alternative is to configure queues to page all messages (both
## persistent and transient) to disk as quickly
## as possible, see http://rabbitmq.com/lazy-queues.html.
##
# vm_memory_high_watermark_paging_ratio = 0.5

## Selects Erlang VM memory consumption calculation strategy. Can be `allocated`, `rss` or `legacy` (aliased as `erlang`),
## Introduced in 3.6.11. `rss` is the default as of 3.6.12.
## See https://github.com/rabbitmq/rabbitmq-server/issues/1223 and rabbitmq/rabbitmq-common#224 for background.
# vm_memory_calculation_strategy = rss

## Interval (in milliseconds) at which we perform the check of the memory
## levels against the watermarks.
##
# memory_monitor_interval = 2500

## The total memory available can be calculated from the OS resources
## - default option - or provided as a configuration parameter.
# total_memory_available_override_value = 2GB

## Set disk free limit (in bytes). Once free disk space reaches this
## lower bound, a disk alarm will be set - see the documentation
## listed above for more details.
##
## Absolute watermark will be ignored if relative is defined!
# disk_free_limit.absolute = 50000

## Or you can set it using memory units (same as in vm_memory_high_watermark)
## with RabbitMQ 3.6.0+.
# disk_free_limit.absolute = 500KB
# disk_free_limit.absolute = 50mb
# disk_free_limit.absolute = 5GB

## Alternatively, we can set a limit relative to total available RAM.
##
## Values lower than 1.0 can be dangerous and should be used carefully.
# disk_free_limit.relative = 2.0

##
## Clustering
## =====================
##
# cluster_partition_handling = ignore

## pause_if_all_down strategy require additional configuration
# cluster_partition_handling = pause_if_all_down

## Recover strategy. Can be either 'autoheal' or 'ignore'
# cluster_partition_handling.pause_if_all_down.recover = ignore

## Node names to check
# cluster_partition_handling.pause_if_all_down.nodes.1 = rabbit@localhost
# cluster_partition_handling.pause_if_all_down.nodes.2 = hare@localhost

## Mirror sync batch size, in messages. Increasing this will speed
## up syncing but total batch size in bytes must not exceed 2 GiB.
## Available in RabbitMQ 3.6.0 or later.
##
# mirroring_sync_batch_size = 4096

## Make clustering happen *automatically* at startup. Only applied
## to nodes that have just been reset or started for the first time.
##
## Relevant doc guide: http://rabbitmq.com//cluster-formation.html
##

# cluster_formation.peer_discovery_backend     = rabbit_peer_discovery_classic_config
#
# cluster_formation.classic_config.nodes.1 = rabbit1@hostname
# cluster_formation.classic_config.nodes.2 = rabbit2@hostname
# cluster_formation.classic_config.nodes.3 = rabbit3@hostname
# cluster_formation.classic_config.nodes.4 = rabbit4@hostname

## DNS-based peer discovery. This backend will list A records
## of the configured hostname and perform reverse lookups for
## the addresses returned.

# cluster_formation.peer_discovery_backend = rabbit_peer_discovery_dns
# cluster_formation.dns.hostname = discovery.eng.example.local

## This node's type can be configured. If you are not sure
## what node type to use, always use 'disc'.
# cluster_formation.node_type = disc

## Interval (in milliseconds) at which we send keepalive messages
## to other cluster members. Note that this is not the same thing
## as net_ticktime; missed keepalive messages will not cause nodes
## to be considered down.
##
# cluster_keepalive_interval = 10000

##
## Statistics Collection
## =====================
##

## Set (internal) statistics collection granularity.
##
## Can be none, coarse or fine
# collect_statistics = none

# collect_statistics = coarse

## Statistics collection interval (in milliseconds). Increasing
## this will reduce the load on management database.
##
# collect_statistics_interval = 5000

##
## Misc/Advanced Options
## =====================
##
## NB: Change these only if you understand what you are doing!
##

## Explicitly enable/disable hipe compilation.
##
# hipe_compile = false

## Timeout used when waiting for Mnesia tables in a cluster to
## become available.
##
# mnesia_table_loading_retry_timeout = 30000

## Retries when waiting for Mnesia tables in the cluster startup. Note that
## this setting is not applied to Mnesia upgrades or node deletions.
##
# mnesia_table_loading_retry_limit = 10

## Size in bytes below which to embed messages in the queue index.
## Related doc guide: http://rabbitmq.com/persistence-conf.html
##
# queue_index_embed_msgs_below = 4096

## You can also set this size in memory units
##
# queue_index_embed_msgs_below = 4kb

## Whether or not to enable background periodic forced GC runs for all
## Erlang processes on the node in "waiting" state.
##
## Disabling background GC may reduce latency for client operations,
## keeping it enabled may reduce median RAM usage by the binary heap
## (see https://www.erlang-solutions.com/blog/erlang-garbage-collector.html).
##
## Before trying this option, please take a look at the memory
## breakdown (http://www.rabbitmq.com/memory-use.html).
##
# background_gc_enabled = false

## Target (desired) interval (in milliseconds) at which we run background GC.
## The actual interval will vary depending on how long it takes to execute
## the operation (can be higher than this interval). Values less than
## 30000 milliseconds are not recommended.
##
# background_gc_target_interval = 60000

## Whether or not to enable proxy protocol support.
## Once enabled, clients cannot directly connect to the broker
## anymore. They must connect through a load balancer that sends the
## proxy protocol header to the broker at connection time.
## This setting applies only to AMQP clients, other protocols
## like MQTT or STOMP have their own setting to enable proxy protocol.
## See the plugins documentation for more information.
##
# proxy_protocol = false

## ----------------------------------------------------------------------------
## Advanced Erlang Networking/Clustering Options.
##
## Related doc guide: http://rabbitmq.com/clustering.html
## ----------------------------------------------------------------------------

# ======================================
# Kernel section
# ======================================

# net_ticktime = 60

## ----------------------------------------------------------------------------
## RabbitMQ Management Plugin
##
## Related doc guide: http://rabbitmq.com/management.html.
## ----------------------------------------------------------------------------

# =======================================
# Management section
# =======================================

## Preload schema definitions from the following JSON file.
## Related doc guide: http://rabbitmq.com/management.html#load-definitions.
##
# management.load_definitions = /path/to/exported/definitions.json

## Log all requests to the management HTTP API to a file.
##
# management.http_log_dir = /path/to/access.log

## Change the port on which the HTTP listener listens,
## specifying an interface for the web server to bind to.
## Also set the listener to use TLS and provide TLS options.
##

management.listener.port = 15672
# management.listener.ip   = 127.0.0.1
# management.listener.ssl  = true

# management.listener.ssl_opts.cacertfile = /path/to/cacert.pem
# management.listener.ssl_opts.certfile   = /path/to/cert.pem
# management.listener.ssl_opts.keyfile    = /path/to/key.pem

## One of 'basic', 'detailed' or 'none'. See
## http://rabbitmq.com/management.html#fine-stats for more details.
# management.rates_mode = basic

## Configure how long aggregated data (such as message rates and queue
## lengths) is retained. Please read the plugin's documentation in
## http://rabbitmq.com/management.html#configuration for more
## details.
## Your can use 'minute', 'hour' and 'day' keys or integer key (in seconds)
# management.sample_retention_policies.global.minute    = 5
# management.sample_retention_policies.global.hour  = 60
# management.sample_retention_policies.global.day = 1200

# management.sample_retention_policies.basic.minute   = 5
# management.sample_retention_policies.basic.hour = 60

# management.sample_retention_policies.detailed.10 = 5

## ----------------------------------------------------------------------------
## RabbitMQ Shovel Plugin
##
## Related doc guide: http://rabbitmq.com/shovel.html
## ----------------------------------------------------------------------------

## See advanced.config.example for a Shovel plugin example


## ----------------------------------------------------------------------------
## RabbitMQ STOMP Plugin
##
## Related doc guide: http://rabbitmq.com/stomp.html
## ----------------------------------------------------------------------------

# =======================================
# STOMP section
# =======================================

## Network Configuration. The format is generally the same as for the core broker.
##
# stomp.listeners.tcp.default = 61613

## Same for ssl listeners
##
# stomp.listeners.ssl.default = 61614

## Number of Erlang processes that will accept connections for the TCP
## and TLS listeners.
##
# stomp.num_acceptors.tcp = 10
# stomp.num_acceptors.ssl = 1

## Additional TLS options

## Extract a name from the client's certificate when using TLS.
##
# stomp.ssl_cert_login = true

## Set a default user name and password. This is used as the default login
## whenever a CONNECT frame omits the login and passcode headers.
##
## Please note that setting this will allow clients to connect without
## authenticating!
##
# stomp.default_user = guest
# stomp.default_pass = guest

## If a default user is configured, or you have configured use TLS client
## certificate based authentication, you can choose to allow clients to
## omit the CONNECT frame entirely. If set to true, the client is
## automatically connected as the default user or user supplied in the
## TLS certificate whenever the first frame sent on a session is not a
## CONNECT frame.
##
# stomp.implicit_connect = true

## Whether or not to enable proxy protocol support.
## Once enabled, clients cannot directly connect to the broker
## anymore. They must connect through a load balancer that sends the
## proxy protocol header to the broker at connection time.
## This setting applies only to STOMP clients, other protocols
## like MQTT or AMQP have their own setting to enable proxy protocol.
## See the plugins or broker documentation for more information.
##
# stomp.proxy_protocol = false

## ----------------------------------------------------------------------------
## RabbitMQ MQTT Adapter
##
## See https://github.com/rabbitmq/rabbitmq-mqtt/blob/stable/README.md
## for details
## ----------------------------------------------------------------------------

# =======================================
# MQTT section
# =======================================

## Set the default user name and password used for anonymous connections (when client
## provides no credentials). Anonymous connections are highly discouraged!
##
# mqtt.default_user = guest
# mqtt.default_pass = guest

## Enable anonymous connections. If this is set to false, clients MUST provide
## credentials in order to connect. See also the mqtt.default_user/mqtt.default_pass
## keys. Anonymous connections are highly discouraged!
##
# mqtt.allow_anonymous = true

## If you have multiple vhosts, specify the one to which the
## adapter connects.
##
# mqtt.vhost = /

## Specify the exchange to which messages from MQTT clients are published.
##
# mqtt.exchange = amq.topic

## Specify TTL (time to live) to control the lifetime of non-clean sessions.
##
# mqtt.subscription_ttl = 1800000

## Set the prefetch count (governing the maximum number of unacknowledged
## messages that will be delivered).
##
# mqtt.prefetch = 10

## TCP/SSL Configuration (as per the broker configuration).
##
# mqtt.listeners.tcp.default = 1883

## Same for ssl listener
##
# mqtt.listeners.ssl.default = 1884

## Number of Erlang processes that will accept connections for the TCP
## and TLS listeners.
##
# mqtt.num_acceptors.tcp = 10
# mqtt.num_acceptors.ssl = 10

## TCP listener options (as per the broker configuration).
##
# mqtt.tcp_listen_options.backlog = 128
# mqtt.tcp_listen_options.nodelay = true

## Whether or not to enable proxy protocol support.
## Once enabled, clients cannot directly connect to the broker
## anymore. They must connect through a load balancer that sends the
## proxy protocol header to the broker at connection time.
## This setting applies only to STOMP clients, other protocols
## like STOMP or AMQP have their own setting to enable proxy protocol.
## See the plugins or broker documentation for more information.
##
# mqtt.proxy_protocol = false

## ----------------------------------------------------------------------------
## RabbitMQ AMQP 1.0 Support
##
## See https://github.com/rabbitmq/rabbitmq-amqp1.0/blob/stable/README.md.
## ----------------------------------------------------------------------------

# =======================================
# AMQP 1.0 section
# =======================================


## Connections that are not authenticated with SASL will connect as this
## account. See the README for more information.
##
## Please note that setting this will allow clients to connect without
## authenticating!
##
# amqp1_0.default_user = guest

## Enable protocol strict mode. See the README for more information.
##
# amqp1_0.protocol_strict_mode = false

## Logging settings.
##
## See http://rabbitmq.com/logging.html and https://github.com/erlang-lager/lager for details.
##

## Log direcrory, taken from the RABBITMQ_LOG_BASE env variable by default.
##
# log.dir = /var/log/rabbitmq

## Logging to file. Can be false or a filename.
## Default:
# log.file = rabbit.log

## To disable logging to a file
# log.file = false

## Log level for file logging
##
# log.file.level = info

## File rotation config. No rotation by default.
## DO NOT SET rotation date to ''. Leave the value unset if "" is the desired value
# log.file.rotation.date = $D0
# log.file.rotation.size = 0

## Logging to console (can be true or false)
##
# log.console = false

## Log level for console logging
##
# log.console.level = info

## Logging to the amq.rabbitmq.log exchange (can be true or false)
##
# log.exchange = false

## Log level to use when logging to the amq.rabbitmq.log exchange
##
# log.exchange.level = info

## ----------------------------------------------------------------------------
## RabbitMQ LDAP Plugin
##
## Related doc guide: http://rabbitmq.com/ldap.html.
##
## ----------------------------------------------------------------------------

# =======================================
# LDAP section
# =======================================

##
## Connecting to the LDAP server(s)
## ================================
##

## Specify servers to bind to. You *must* set this in order for the plugin
## to work properly.
##
# auth_ldap.servers.1 = your-server-name-goes-here

## You can define multiple servers
# auth_ldap.servers.2 = your-other-server

## Connect to the LDAP server using TLS
##
# auth_ldap.use_ssl = false

## Specify the LDAP port to connect to
##
# auth_ldap.port = 389

## LDAP connection timeout, in milliseconds or 'infinity'
##
# auth_ldap.timeout = infinity

## Or number
# auth_ldap.timeout = 500

## Enable logging of LDAP queries.
## One of
##   - false (no logging is performed)
##   - true (verbose logging of the logic used by the plugin)
##   - network (as true, but additionally logs LDAP network traffic)
##
## Defaults to false.
##
# auth_ldap.log = false

## Also can be true or network
# auth_ldap.log = true
# auth_ldap.log = network

##
## Authentication
## ==============
##

## Pattern to convert the username given through AMQP to a DN before
## binding
##
# auth_ldap.user_dn_pattern = cn=${username},ou=People,dc=example,dc=com

## Alternatively, you can convert a username to a Distinguished
## Name via an LDAP lookup after binding. See the documentation for
## full details.

## When converting a username to a dn via a lookup, set these to
## the name of the attribute that represents the user name, and the
## base DN for the lookup query.
##
# auth_ldap.dn_lookup_attribute = userPrincipalName
# auth_ldap.dn_lookup_base      = DC=gopivotal,DC=com

## Controls how to bind for authorisation queries and also to
## retrieve the details of users logging in without presenting a
## password (e.g., SASL EXTERNAL).
## One of
##  - as_user (to bind as the authenticated user - requires a password)
##  - anon    (to bind anonymously)
##  - {UserDN, Password} (to bind with a specified user name and password)
##
## Defaults to 'as_user'.
##
# auth_ldap.other_bind = as_user

## Or can be more complex:
# auth_ldap.other_bind.user_dn  = User
# auth_ldap.other_bind.password = Password

## If user_dn and password defined - other options is ignored.

# -----------------------------
# Too complex section of LDAP
# -----------------------------

##
## Authorisation
## =============
##

## The LDAP plugin can perform a variety of queries against your
## LDAP server to determine questions of authorisation.
##
## Related doc guide: http://rabbitmq.com/ldap.html#authorisation.

## Following configuration should be defined in additional.config file
## DO NOT UNCOMMENT THIS LINES!

## Set the query to use when determining vhost access
##
## {vhost_access_query, {in_group,
##                       "ou=${vhost}-users,ou=vhosts,dc=example,dc=com"}},

## Set the query to use when determining resource (e.g., queue) access
##
## {resource_access_query, {constant, true}},

## Set queries to determine which tags a user has
##
## {tag_queries, []}
#   ]},
# -----------------------------
```

> 每个实例都进行这样的配置， 防止在单机上启动多个实例导致端口绑定冲突

启动之后， 添加用户， 用于登录到web管理界面：

```bash
rabbitmqctl list_users #查看用户
rabbitmqctl add_user admin 123456
rabbitmqctl set_user_tags admin administrator
rabbitmqctl set_permissions -p / admin ".*" ".*" ".*" # 设置读写权限
```

添加完用户可以启用一些插件：

```bash
# rabbitmq web插件
rabbitmq-plugins enable rabbitmq_management
rabbitmq-plugins enable rabbitmq_mqtt
# rabbitmq mqtt插件
rabbitmq-plugins enable rabbitmq_web_mqtt
# 延迟队列插件 需要额外下载， 不自带
rabbitmq-plugins enable rabbitmq_delayed_message_exchange
# 查看启用了哪些插件
rabbitmq-plugins list
```



### rabbitMQ集群搭建

>  单个实例安装完成后， 需要通过在命令行指定启动环境变量的方式来启动其他实例， 否则会出现端口冲突.启动方式可以参考[(8条消息) RabbitMQ单机集群搭建_Java_HuiLong的博客-CSDN博客](https://blog.csdn.net/Java_HuiLong/article/details/73718714?depth_1-)

下面是针对mqtt集群搭建启动方式：

node1

```bash
RABBITMQ_NODE_PORT=5673 RABBITMQ_SERVER_START_ARGS="-rabbitmq_management listener [{port,15673}] -rabbitmq_stomp tcp_listeners [61613] -rabbitmq_mqtt  tcp_listeners [1885]" RABBITMQ_NODENAME=rabbit1 ./rabbitmq-server -detached
```

node2

```bash
RABBITMQ_NODE_PORT=5674 RABBITMQ_SERVER_START_ARGS="-rabbitmq_management listener [{port,15674}] -rabbitmq_stomp tcp_listeners [61614] -rabbitmq_mqtt  tcp_listeners [1886]" RABBITMQ_NODENAME=rabbit2 ./rabbitmq-server -detached
```

node3

```bash
RABBITMQ_NODE_PORT=5675 RABBITMQ_SERVER_START_ARGS="-rabbitmq_management listener [{port,15675}] -rabbitmq_stomp tcp_listeners [61615] -rabbitmq_mqtt tcp_listeners [1887]" RABBITMQ_NODENAME=rabbit3 ./rabbitmq-server -detached
```

这样就能启动三个node。将三个节点链家起来成为集群， 需要进一步操作：

停掉node2

```bash
./rabbitmqctl -n rabbit2 stop_app
```

清除节点2的数据

```bash
./rabbitmqctl -n rabbit2 reset
```

加入集群

```bash
./rabbitmqctl -n rabbit2 join_cluster rabbit1 --ram
```

对于node3也是同样的操作：

停掉node3

```bash
./rabbitmqctl -n rabbit3 stop_app
```

清除节点2的数据

```bash
./rabbitmqctl -n rabbit3 reset
```

加入集群

```bash
./rabbitmqctl -n rabbit3 join_cluster rabbit1 --ram
```



