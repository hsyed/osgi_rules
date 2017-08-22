load("//osgi:rules.bzl", "osgi_dm_plugin_library", "wrapped_osgi_library")

java_library(
    name = "jar",
    srcs = (glob(["src/**/*.java"])),
    deps = [
        "//third_party:felix-dm-annotations",
        ":slf4j",
        "//third_party:slf4j-nop",
    ],
)

osgi_dm_plugin_library(
    name = "plugin",
    bsn = "nihao_world",
    jar = ":jar",
)

wrapped_osgi_library(
    name = "slf4j",
    jar = "@org_slf4j_slf4j_api//jar",
)
