(ns ns-example.core
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]))

;(defn print-args [arg]
;  (println "print-args function called with arg: " arg))
;
;(def place {:name "ust" :location "hk" :description "a university"})
;
;(defn try-destructure [{:keys [name location description] :as place}]
;  (println name location description place))
;
;(map (partial + 1) [1 2 3 4 5])
;
;(map #(partial + %) [1 2 3])
;
;#(+ 1 %)
;
;(repeatedly 5 #(+ 1 %))

(def multi-line "
Indian Administrative Service
Bhāratīya Praśāsanika Sevā
Service overview
IAS (Central Association) logo.jpeg
Formerly known as	Imperial Civil Service (ICS)
Founded	1858; 161 years ago
(as Imperial Civil Service)
26 January 1950; 68 years ago
(as Indian Administrative Service)
Country	India
Staff college	Lal Bahadur Shastri National Academy of Administration, Mussoorie, Uttarakhand
Cadre controlling authority	Department of Personnel and Training, Ministry of Personnel, Public Grievances and Pensions
Minister responsible	Narendra Modi, Prime Minister of India and Minister of Personnel, Public Grievances and Pensions
Legal personality	Governmental; civil service
Duties	Policy formulation
Policy implementation
Public administration
Bureaucratic governance
Secretarial assistance (Centre and State)
Preceding service	Imperial Civil Service (1858–1946)
Cadre strength	4,926 members (3,511 officers directly recruited by the Union Public Service Commission and 1,415 officers promoted from state civil services)[1][2]
Selection	Civil Services Examination
Association	IAS (Central) Association
Head of the civil services
Cabinet Secretary of India	Pradeep Kumar Sinha, IAS
India
Emblem of India.svg
This article is part of a series on the
politics and government of
India
Constitution and law[show]
Government of India[show]
Elections[show]
Federalism[show]
Other countries Atlas
vte
The Indian Administrative Service (IAST: Bhāratīya Praśāsanika Sevā), often abbreviated to I.A.S., or simply IAS, is the administrative arm of the All India Services.[3] Considered the premier civil service of India,[3][4] the IAS is one of the three arms of the All India Services along with the Indian Police Service (IPS) and the Indian Forest Service (IFS). Members of these three services serve the Government of India as well as the individual states. IAS officers may also be deployed to various public sector undertakings.")

;; This one is good, because it escape the \n
;; The original version do not escape any \n
(def multi-line-format "\\nIndian Administrative Service\\nBh?rat?ya Pra??sanika Sev?\\nService overview\\nIAS (Central Association) logo.jpeg\\nFormerly known as\\tImperial Civil Service (ICS)\\nFounded\\t1858; 161 years ago\\n(as Imperial Civil Service)\\n26 January 1950; 68 years ago\\n(as Indian Administrative Service)\\nCountry\\tIndia\\nStaff college\\tLal Bahadur Shastri National Academy of Administration, Mussoorie, Uttarakhand\\nCadre controlling authority\\tDepartment of Personnel and Training, Ministry of Personnel, Public Grievances and Pensions\\nMinister responsible\\tNarendra Modi, Prime Minister of India and Minister of Personnel, Public Grievances and Pensions\\nLegal personality\\tGovernmental; civil service\\nDuties\\tPolicy formulation\\nPolicy implementation\\nPublic administration\\nBureaucratic governance\\nSecretarial assistance (Centre and State)\\nPreceding service\\tImperial Civil Service (1858?1946)\\nCadre strength\\t4,926 members (3,511 officers directly recruited by the Union Public Service Commission and 1,415 officers promoted from state civil services)[1][2]\\nSelection\\tCivil Services Examination\\nAssociation\\tIAS (Central) Association\\nHead of the civil services\\nCabinet Secretary of India\\tPradeep Kumar Sinha, IAS\\nIndia\\nEmblem of India.svg\\nThis article is part of a series on the\\npolitics and government of\\nIndia\\nConstitution and law[show]\\nGovernment of India[show]\\nElections[show]\\nFederalism[show]\\nOther countries Atlas\\nvte\\nThe Indian Administrative Service (IAST: Bh?rat?ya Pra??sanika Sev?), often abbreviated to I.A.S., or simply IAS, is the administrative arm of the All India Services.[3] Considered the premier civil service of India,[3][4] the IAS is one of the three arms of the All India Services along with the Indian Police Service (IPS) and the Indian Forest Service (IFS). Members of these three services serve the Government of India as well as the individual states. IAS officers may also be deployed to various public sector undertakings.")

(def xf (juxt :company_job/item_id
              :company_job/state
              :user_company/is_company_suspended
              :company_job/is_job_suspended
              :company_job/job_description
              :company_job/job_promotion_state))

(def item {:company_job/is_job_suspended false, :user_company/is_company_suspended false, :company_job/item_id "bd0f2e64-7926-4a96-9e0e-46a7df741ce6", :company_job/state "Closed", :company_job/job_promotion_state nil, :company_job/job_description "\nIndian Administrative Service\nBh?rat?ya Pra??sanika Sev?\nService overview\nIAS (Central Association) logo.jpeg\nFormerly known as\tImperial Civil Service (ICS)\nFounded\t1858; 161 years ago\n(as Imperial Civil Service)\n26 January 1950; 68 years ago\n(as Indian Administrative Service)\nCountry\tIndia\nStaff college\tLal Bahadur Shastri National Academy of Administration, Mussoorie, Uttarakhand\nCadre controlling authority\tDepartment of Personnel and Training, Ministry of Personnel, Public Grievances and Pensions\nMinister responsible\tNarendra Modi, Prime Minister of India and Minister of Personnel, Public Grievances and Pensions\nLegal personality\tGovernmental; civil service\nDuties\tPolicy formulation\nPolicy implementation\nPublic administration\nBureaucratic governance\nSecretarial assistance (Centre and State)\nPreceding service\tImperial Civil Service (1858?1946)\nCadre strength\t4,926 members (3,511 officers directly recruited by the Union Public Service Commission and 1,415 officers promoted from state civil services)[1][2]\nSelection\tCivil Services Examination\nAssociation\tIAS (Central) Association\nHead of the civil services\nCabinet Secretary of India\tPradeep Kumar Sinha, IAS\nIndia\nEmblem of India.svg\nThis article is part of a series on the\npolitics and government of\nIndia\nConstitution and law[show]\nGovernment of India[show]\nElections[show]\nFederalism[show]\nOther countries Atlas\nvte\nThe Indian Administrative Service (IAST: Bh?rat?ya Pra??sanika Sev?), often abbreviated to I.A.S., or simply IAS, is the administrative arm of the All India Services.[3] Considered the premier civil service of India,[3][4] the IAS is one of the three arms of the All India Services along with the Indian Police Service (IPS) and the Indian Forest Service (IFS). Members of these three services serve the Government of India as well as the individual states. IAS officers may also be deployed to various public sector undertakings.", :company_job/employment_type "Temp", :job_type '("658bfcdb-d268-4906-b146-c5bcbed56fff"), :district '("1d09ae94-3797-4166-9a4f-e3c9932d8f00")})

;(defn -main []
;  (with-open [writer (io/writer "out-file.csv")]
;    (csv/write-csv writer
;                   [["job name" "job description"]
;                    ["ghi" multi-line-format]])))

;(defn -main []
;  (with-open [writer (io/writer "out-file.csv")]
;    (csv/write-csv writer
;                   [["job name" "job description"]
;                    ["new job" (clojure.string/trim-newline (:company_job/job_description item))]])))

(defn -main []
  (prn (clojure.string/trim-newline (:company_job/job_description item)))
  (prn [(xf item)]))