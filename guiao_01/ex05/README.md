During this exercise I found myself having a lot of difficulties / facing problems when solving it. Specially when it came to the logic of how to do it.

As we can observe, exercise 1.5 a) was solved in an inefficient way and looks pretty ugly.
It could've been solved with the usage of multiple entries or a simple sorted set where we would save the timestamp + product.

The same thing happens for 1.5 b). Even though it is a lot better than the previous exercise we sadly encounter a problem which I only noticed a little bit too late during it's development (Sorted sets are based on score, and quantities don't work well with the way I implemented it).
So even though it works, it only half works due to it's sorting.
This was caused by misinterpreting the documentation.

Lastly we have the 1.5 b) version_2 which is simpler but gets the job done.