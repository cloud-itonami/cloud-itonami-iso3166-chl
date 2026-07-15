(ns statute.facts
  "General-law compliance catalog for Chile (CHL) -- extends this repo's
  existing `marketentry.facts` (public-procurement market-entry only,
  narrow scope) with a second, orthogonal catalog of statutes a company
  generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  bcn.cl/leychile.cl (Biblioteca del Congreso Nacional's own interactive
  law-browsing pages, the usual first-choice host) returned a JS-only
  'connection may be slow / browser not compatible' error to WebFetch on
  every navegar URL tried -- a failure mode matching e-Gov and
  fedlex.admin.ch elsewhere in this family. Two entries were instead
  verified via BCN's own PDF export service (nuevo.leychile.cl, which
  DID render as readable text) and via the Dirección del Trabajo's own
  official PDF republication. A law not in this table has NO spec-basis,
  full stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"CHL"
   [{:statute/id "chl.ley-sociedades-anonimas-18046"
     :statute/title "Ley N° 18.046 sobre Sociedades Anónimas"
     :statute/jurisdiction "CHL"
     :statute/kind :law
     :statute/law-number "Ley N° 18.046"
     :statute/url "https://nuevo.leychile.cl/servicios/Consulta/Exportar?radioExportar=Normas&exportar_formato=pdf&nombrearchivo=Ley-18046_22-OCT-1981&exportar_con_notas_bcn=True&exportar_con_notas_originales=True&exportar_con_notas_al_pie=True&hddResultadoExportar=29473.2023-02-03.0.0%23"
     :statute/url-provenance :official-bcn-leychile
     :statute/enacted-date "1981-10-22"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "chl.ley-proteccion-vida-privada-19628"
     :statute/title "Ley N° 19.628 sobre Protección de la Vida Privada o Protección de Datos de Carácter Personal"
     :statute/jurisdiction "CHL"
     :statute/kind :law
     :statute/law-number "Ley N° 19.628"
     :statute/url "https://www.dipres.gob.cl/598/articles-51683_Otrasleyes_ley19628.pdf"
     :statute/url-provenance :official-chile-gov-mirror
     :statute/enacted-date "1999-08-28"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "chl.codigo-del-trabajo-dfl1-2002"
     :statute/title "Código del Trabajo (DFL N° 1, incluye Ley Orgánica de la Dirección del Trabajo)"
     :statute/jurisdiction "CHL"
     :statute/kind :law
     :statute/law-number "DFL N° 1 (2002)"
     :statute/url "https://www.dt.gob.cl/legislacion/1624/articles-95516_recurso_1.pdf"
     :statute/url-provenance :official-direccion-del-trabajo
     :statute/enacted-date "2003-01-16"
     :statute/last-revised-date "2026-07"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

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
      :note (str "cloud-itonami-iso3166-chl statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "CHL")) " CHL statutes seeded with "
                 "official BCN/Direccion del Trabajo citations. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
