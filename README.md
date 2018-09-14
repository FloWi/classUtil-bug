# classUtil-bug

When running `sbt test` I run into this exception:
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

The code at this location checks for the correct version of ASM
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
