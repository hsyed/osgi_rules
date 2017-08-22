def _bnd_impl(ctx):
  instructions=''

  for k,v in ctx.attr.instructions.items():
    instructions += "%s=%s\n" % (k,v)

  ctx.actions.run(
      inputs=[ctx.file.source],
      outputs=[ctx.outputs.osgi_jar],
      arguments=[ctx.file.source.path, ctx.outputs.osgi_jar.path, instructions],
      progress_message="using bnd to produce osgi jar: %s" % ctx.attr.name,
      executable=ctx.executable._bnd_exec
  )
  # return a java provider. usefull info here: https://blog.bazel.build/2017/03/07/java-sandwich.html
  deps =[]
  if java_common.provider in ctx.attr.source:
    deps.append(ctx.attr.source[java_common.provider])
  deps_provider = java_common.merge(deps)
  return struct(
    providers = [deps_provider]
  )

bnd = rule(
    attrs = {
        "source": attr.label(allow_single_file = True),
        "instructions": attr.string_dict(),
        "_bnd_exec": attr.label(
            default = Label("//osgi/fwd/bnd:bin"),
            executable = True,
            cfg = "target",
        ),
    },
    fragments = ["java"],
    outputs = {
        "osgi_jar": "lib%{name}-osgi.jar",
    },
    implementation = _bnd_impl,
)
