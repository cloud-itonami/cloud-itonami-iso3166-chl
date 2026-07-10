(ns marketentry.facts "Chile market-entry catalog.")
(def catalog
  {"CHL" {:name "Chile"
          :owner-authority "ChileCompra / Mercado Público"
          :legal-basis "Ley de Compras Públicas"
          :national-spec "Mercado Público supplier registration + RUT"
          :provenance "https://www.mercadopublico.cl/"
          :required-evidence ["RUT record" "ChileCompra registration record" "SII extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / ChileCompra"
          :rep-legal-basis "Chilean RUT entity typically required for Mercado Público awards"
          :rep-provenance "https://www.mercadopublico.cl/"
          :corporate-number-owner-authority "SII / Registro de Comercio"
          :corporate-number-legal-basis "RUT"
          :corporate-number-provenance "https://www.sii.cl/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "COL" {:name "Colombia" :owner-authority "SECOP II" :legal-basis "Ley 80" :national-spec "SECOP II" :provenance "https://www.colombiacompra.gov.co/"
          :required-evidence ["NIT record" "SECOP II registration" "RUES extract" "Authorized-representative record"]}
   "PER" {:name "Peru" :owner-authority "OSCE/SEACE" :legal-basis "Ley de Contrataciones" :national-spec "SEACE" :provenance "https://www.seace.gob.pe/"
          :required-evidence ["RUC record" "SEACE registration" "SUNAT extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
