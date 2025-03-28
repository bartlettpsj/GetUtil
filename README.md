# Lodash get in Java

Small static class to handle Lodash.get() using Introspection (Runtime).

Will support:

main.java.com.iqss.LodashGet.get(obj, "a.b[1].c.d[2]");

Where b is an array of objects and d is an array of something, such as integers (technically could be an object reference too).

Reduces the need for a) lots of null checking (Java where is your null coalescing) and b) strongly typed "hardcoded" references.

Note:

I should do testing properly rather than this over simplified hack.  

Not sure the performance hit using Introspection here.  It will be significant compared to direct compiled code references.
