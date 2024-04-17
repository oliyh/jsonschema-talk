(ns reveal.slides
  (:require [reveal.styles :as styles :refer [style]]))

(def title-page
  [:section
   {:data-background-image "img/title-screen.jpg"}
   [:h1.r-fit-text (style {:color "#111"
                           :text-shadow "0 0 10px #fff, 0 0 20px #fff, 0 0 30px yellow "})
    "Sunshine"]
   [:h5 (style {:margin-top "5em"
                :color "#111"
                :text-shadow "0 0 10px #fff, 0 0 20px #fff, 0 0 30px yellow "})
    "A year of solar power"]
   [:audio
    {:controls true :data-autoplay true}
    [:source {:src "audio/sunshine-theme.mp3"
              :type "audio/mpeg"}]]])

(def intro
  [:section
   [:h2 "My home the power plant"]
   [:img.r-stretch.frame {:src "img/house.jpg"}]
   [:aside.notes
    [:ul
     [:li "One year ago we had solar panels installed"]
     [:li "I've learned a lot and been asked for advice"]
     [:li "Here are all the calculations and quantified reasoning"]]]])

(defn all []
  [title-page
   intro])
