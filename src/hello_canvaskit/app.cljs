(ns hello-canvaskit.app
  (:require ["canvaskit-wasm" :as ck]))

;; Run this function to see a rectangle show up in the app
;; This is also wired up in the `init` function, but that's only
;; run when the app starts up/is refreshed.
;; To tinker with the Canvas display without restarting the appear
;; change this function, run it in the REPL and verify the result.
;; In that case, `shadow-cljs` will take care of hot-reloading the app.

;; TODO:- Clean up code via
;;        1. Using `js->clj`
;;        2. Using  `->` macro
(defn use-ck []
  (let [ck-loaded (ck)]
    (.then ck-loaded
           (fn [ck]
             (let [surface ^js (.MakeCanvasSurface ck "foo")
                   paint   (new ^js (.-Paint ck))
                   rr      ^js (.RRectXY ck
                                         ^js (.LTRBRect ck
                                                        10
                                                        60
                                                        210
                                                        260)
                                25
                                15)
                   draw    (fn [canvas]
                             (.clear canvas
                                     ^js (.-WHITE ck))
                             ^js (.drawRRect canvas
                                             rr
                                             paint))]
               ^js (.setColor paint ^js (.Color4f ck
                                                  0.9
                                                  0
                                                  0 1.0))
               ^js (.setStyle paint ^js (.-Stroke ^js (.-PaintStyle ck)))
               ^js (.setAntiAlias paint true)
               (.drawOnce surface draw)
               (.log js/console "drawn"))))))


(defn init []
  (println "Hello Canvaskit World!")
  (use-ck))

(use-ck)

