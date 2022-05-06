import { Sequelize } from 'sequelize';


const database = process.env.POSTGRES_DB ?? 'auth-db';
const username = process.env.POSTGRES_USE ?? 'postgres';
const password = process.env.POSTGRES_PASSWORD ?? 'postgres';


const sequelize = new Sequelize(database, username, password, {
  host: process.env.POSTGRES_BASE_URL ?? 'localhost',
  port: process.env.POSTGRES_PORT ?? 35432,
  dialect: 'postgres',
  quoteIdentifiers: false,
  define: {
    syncOnAssociation: true,
    timestamps: false,
    underscored: true,
    underscoredAll: true,
    freezeTableName: true,
  },
});

sequelize.authenticate()
    .then(() => console.info('Connection has been established!'))
    .catch((err) => console.error(`Unable to connect to the database: ${ err.message }`));

export default sequelize;



