if (process.env.NODE_ENV === "production") {
    const opt = require("./ant-design-admin-panel-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./ant-design-admin-panel-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./ant-design-admin-panel-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
