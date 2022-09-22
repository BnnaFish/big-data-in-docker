CREATE TABLE IF NOT EXISTS spark_catalog.default.iris (
    sepal_length REAL,
    sepal_width REAL,
    petal_length REAL,
    petal_width REAL,
    class String
)
USING iceberg
COMMENT 'Contains the responses of a gas multisensor device deployed on the field in an Italian city. Hourly responses averages are recorded along with gas concentrations references from a certified analyzer.'
PARTITIONED BY  (class);
