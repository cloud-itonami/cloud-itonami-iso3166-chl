(ns culture.facts
  "Country-level regional-culture catalog for Chile (CHL) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"CHL"
   [{:culture/id "chl.dish.completo"
     :culture/name "Completo"
     :culture/country "CHL"
     :culture/kind :dish
     :culture/summary "Hot dog variation eaten in Chile, typically with tomato, avocado, mayonnaise or sauerkraut, developed in 1920s Santiago as a distinctly Chilean adaptation."
     :culture/url "https://en.wikipedia.org/wiki/Completo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "chl.dish.curanto"
     :culture/name "Curanto"
     :culture/country "CHL"
     :culture/kind :dish
     :culture/summary "Traditional earth-oven preparation of seafood, potatoes and meats from the Chiloé Archipelago, one of the most recognised dishes of Chilote cuisine, with archaeological remains dating back more than eleven thousand years."
     :culture/url "https://en.wikipedia.org/wiki/Curanto"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "chl.dish.pastel-de-choclo"
     :culture/name "Pastel de choclo"
     :culture/country "CHL"
     :culture/kind :dish
     :culture/summary "South American sweetcorn casserole traditional in the cuisines of Chile and several neighbouring countries; its pino filling is also used in traditional Chilean empanadas."
     :culture/url "https://en.wikipedia.org/wiki/Pastel_de_choclo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "chl.beverage.mote-con-huesillo"
     :culture/name "Mote con huesillo"
     :culture/country "CHL"
     :culture/kind :beverage
     :culture/summary "Traditional Chilean summer-time drink of husked wheat and dried peaches, often sold in street stands or vendor carts."
     :culture/url "https://en.wikipedia.org/wiki/Mote_con_huesillo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "chl.beverage.pisco"
     :culture/name "Pisco"
     :culture/country "CHL"
     :culture/kind :beverage
     :culture/summary "Grape-based spirit produced in winemaking regions of Peru and Chile; both nations claim it as their national drink, and Chile produces roughly three times as much as Peru."
     :culture/url "https://en.wikipedia.org/wiki/Pisco"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "chl.beverage.chilean-wine"
     :culture/name "Chilean wine"
     :culture/country "CHL"
     :culture/kind :beverage
     :culture/summary "Wine produced in Chile since the 16th century, when Spanish conquistadors introduced Vitis vinifera vines; Chile is the fifth largest exporter and seventh largest producer of wine globally."
     :culture/url "https://en.wikipedia.org/wiki/Chilean_wine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "chl.product.merken"
     :culture/name "Merkén"
     :culture/country "CHL"
     :culture/kind :product
     :culture/summary "Smoked chili pepper condiment of Mapuche cuisine, originating primarily from the Araucanía Region of Chile and widely used in Chilean cooking."
     :culture/url "https://en.wikipedia.org/wiki/Merkén"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "chl.craft.chamanto"
     :culture/name "Chamanto"
     :culture/country "CHL"
     :culture/kind :craft
     :culture/summary "Traditional reversible decorative garment from central Chile, similar to a poncho, woven in silk thread and wool with designs including copihues, Chile's national flower."
     :culture/url "https://en.wikipedia.org/wiki/Chamanto"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "chl.festival.fiestas-patrias"
     :culture/name "Fiestas Patrias"
     :culture/country "CHL"
     :culture/kind :festival
     :culture/summary "Chile's most important national celebrations, held on 18-19 September to commemorate the beginning of the Chilean independence process, with cueca dancing and fondas."
     :culture/url "https://en.wikipedia.org/wiki/Fiestas_Patrias_(Chile)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "chl.heritage.rapa-nui"
     :culture/name "Rapa Nui National Park"
     :culture/country "CHL"
     :culture/kind :heritage
     :culture/summary "Protected area on Easter Island, Chile, famous for its moai statues; designated a UNESCO World Heritage Site on 22 March 1996."
     :culture/url "https://en.wikipedia.org/wiki/Rapa_Nui_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-chl culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "CHL"))
                 " CHL entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
