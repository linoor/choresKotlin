(ns clojurescript-chores.prod
  (:require [clojurescript-chores.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
