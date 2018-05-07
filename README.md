I did this just would like to know how much work and concept of load balance implementation.

Load Balance Algorithms(I found in internet):
Round Robin -- just rotated and check health check
Weighted Round Robin -- more weight more messages
Least Connection (Least Used) -- server with the least active session get selected
Weighted Least Connection -- server with the least active session * weighted get selected
Chained Fail over (Priority) -- first server in chain get called until cannot make it, and then second server get called until cannot make it, and then third.
Weighted/Fastest Response Time (Overflow) -- send to server with the fastest response time at particular time related to current time.
Source IP Hash (Persistence) -- keep track of source and destination IP address of client and server and send the message from particular source and destination to the same destination.
Lowest Latency -- Lowest Ping
Random -- random

