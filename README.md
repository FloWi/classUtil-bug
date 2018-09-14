# classUtil-bug

When running `sbt test` I encounter this exception:
```
[info]   java.lang.UnsupportedOperationException:
[info]   at org.objectweb.asm.ClassVisitor.visitModule(ClassVisitor.java:129)
[info]   at org.objectweb.asm.ClassReader.readModule(ClassReader.java:667)
[info]   at org.objectweb.asm.ClassReader.accept(ClassReader.java:503)
[info]   at org.objectweb.asm.ClassReader.accept(ClassReader.java:355)
[info]   at org.clapper.classutil.asm.ClassFile$.load(ClassFinderImpl.scala:261)
[info]   at org.clapper.classutil.ClassFinder.classData(ClassFinder.scala:428)
[info]   at org.clapper.classutil.ClassFinder.$anonfun$processOpenZip$2(ClassFinder.scala:386)
[info]   at scala.collection.immutable.Stream.map(Stream.scala:415)
[info]   at org.clapper.classutil.ClassFinder.processOpenZip(ClassFinder.scala:386)
[info]   at org.clapper.classutil.ClassFinder.processJar(ClassFinder.scala:344)
```

The code at this location (`ClassVisitor.java:129`) checks for the correct version of ASM
```java
  public ModuleVisitor visitModule(final String name, final int access, final String version) {
    if (api < Opcodes.ASM6) {
      throw new UnsupportedOperationException();
    }
    if (cv != null) {
      return cv.visitModule(name, access, version);
    }
    return null;
  }

```

The dependencyTree looks fine though.

```
[info] com.example:scalatest-example_2.12:0.1.0-SNAPSHOT [S]
[info]   +-ch.qos.logback:logback-classic:1.2.3
[info]   | +-ch.qos.logback:logback-core:1.2.3
[info]   | +-org.slf4j:slf4j-api:1.7.25
[info]   |
[info]   +-ch.qos.logback:logback-core:1.2.3
[info]   +-org.clapper:classutil_2.12:1.3.0 [S]
[info]   | +-org.clapper:grizzled-scala_2.12:4.4.2 [S]
[info]   | +-org.ow2.asm:asm-commons:6.1.1
[info]   | | +-org.ow2.asm:asm-analysis:6.1.1
[info]   | | | +-org.ow2.asm:asm-tree:6.1.1
[info]   | | |   +-org.ow2.asm:asm:6.1.1
[info]   | | |
[info]   | | +-org.ow2.asm:asm-tree:6.1.1
[info]   | | | +-org.ow2.asm:asm:6.1.1
[info]   | | |
[info]   | | +-org.ow2.asm:asm:6.1.1
[info]   | |
[info]   | +-org.ow2.asm:asm-util:6.1.1
[info]   | | +-org.ow2.asm:asm-analysis:6.1.1
[info]   | | | +-org.ow2.asm:asm-tree:6.1.1
[info]   | | |   +-org.ow2.asm:asm:6.1.1
[info]   | | |
[info]   | | +-org.ow2.asm:asm-tree:6.1.1
[info]   | | | +-org.ow2.asm:asm:6.1.1
[info]   | | |
[info]   | | +-org.ow2.asm:asm:6.1.1
[info]   | |
[info]   | +-org.ow2.asm:asm:6.1.1
[info]   |
[info]   +-org.clapper:grizzled-scala_2.12:4.4.2 [S]
[info]   +-org.clapper:grizzled-slf4j_2.12:1.3.2 [S]
[info]   | +-org.slf4j:slf4j-api:1.7.25
[info]   | +-org.slf4j:slf4j-api:1.7.9 (evicted by: 1.7.25)
[info]   |
[info]   +-org.ow2.asm:asm-commons:6.1.1
[info]   | +-org.ow2.asm:asm-analysis:6.1.1
[info]   | | +-org.ow2.asm:asm-tree:6.1.1
[info]   | |   +-org.ow2.asm:asm:6.1.1
[info]   | |
[info]   | +-org.ow2.asm:asm-tree:6.1.1
[info]   | | +-org.ow2.asm:asm:6.1.1
[info]   | |
[info]   | +-org.ow2.asm:asm:6.1.1
[info]   |
[info]   +-org.ow2.asm:asm-util:6.1.1
[info]   | +-org.ow2.asm:asm-analysis:6.1.1
[info]   | | +-org.ow2.asm:asm-tree:6.1.1
[info]   | |   +-org.ow2.asm:asm:6.1.1
[info]   | |
[info]   | +-org.ow2.asm:asm-tree:6.1.1
[info]   | | +-org.ow2.asm:asm:6.1.1
[info]   | |
[info]   | +-org.ow2.asm:asm:6.1.1
[info]   |
[info]   +-org.ow2.asm:asm:6.1.1
[info]   +-org.slf4j:slf4j-api:1.7.25
```
