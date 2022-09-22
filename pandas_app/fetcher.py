import pandas as pd
from sqlalchemy import create_engine

engine = create_engine(
    "trino://user@localhost:8080/ml_lake/default",
    connect_args={
        "session_properties": {"query_max_run_time": "1m"},
    },
)

shipment_shipped = pd.read_sql_table("iris", con=engine)

print(shipment_shipped.info())
print(shipment_shipped.head())
