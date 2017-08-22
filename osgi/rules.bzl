load("//osgi/fwd:bnd.bzl", "bnd")

def _fwd_bnd(name, source, bsn, instructions):
  instructions.setdefault("Bundle-Version", "0.0")
  instructions.setdefault("Export-Package", "*")
  instructions["Bundle-SymbolicName"] = bsn
  bnd(name = name, source = source, instructions = instructions)

def osgi_dm_plugin_library(name, jar, bsn, instructions = None):
  if instructions == None:
    instructions = {}
  instructions["-buildpath"] = "org.apache.felix.dependencymanager.annotation"
  instructions["-plugin"] = "org.apache.felix.dm.annotation.plugin.bnd.AnnotationPlugin;log=debug"
  _fwd_bnd(name, jar, bsn, instructions)

def wrapped_osgi_library(name, jar, bsn = "", instructions = None):
  if instructions == None:
      instructions = {}
  if bsn == "":
    bsn = name
  _fwd_bnd(name, jar, bsn, instructions)
