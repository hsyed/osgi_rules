package(default_visibility = ["//visibility:public"])

java_binary(
    name = "bin",
    srcs = glob(["*.java"]),
    main_class = "fwd.bnd.Main",
    deps = [
        "//third_party:bndlib",
        "//third_party:felix-dm-annotations",
        "//third_party:slf4j-nop",
        "//third_party:slf4j-api"
    ],
)
