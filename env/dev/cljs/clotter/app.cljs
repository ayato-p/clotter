(ns ^:figwheel-no-load clotter.app
  (:require [clotter.core :as core]
            [devtools.core :as devtools]))

(enable-console-print!)

(devtools/install!)

(core/init!)
