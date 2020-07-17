package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.Biere;

public class BiereDAOImpl implements IBiereDAO {

	@Override
	public boolean add(Biere pBiere) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pBiere.getDesignation());
			ps.setString(2, pBiere.getDescription());
			ps.setDouble(3, pBiere.getPrix());
			ps.setInt(4, pBiere.getQuantite());
			ps.setBytes(5, pBiere.getPhoto());
			ps.setInt(6, pBiere.getIdPays());
			ps.setInt(7, pBiere.getIdType()); //pBiere.type.getId()
			
			int verifUpdate = ps.executeUpdate();
			
			return (verifUpdate == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de l'ajout de la bière / méthode add() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return false;
	}//end add() - biere

	@Override
	public boolean update(Biere pBiere) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pBiere.getDesignation());
			ps.setString(2, pBiere.getDescription());
			ps.setDouble(3, pBiere.getPrix());
			ps.setInt(4, pBiere.getQuantite());
			ps.setBytes(5, pBiere.getPhoto());
			ps.setInt(6, pBiere.getIdPays());
			ps.setInt(7, pBiere.getIdType());
			ps.setInt(8, pBiere.getIdProduit());
			
			int verifUpdate = ps.executeUpdate();
			
			return (verifUpdate == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la modification de la bière / méthode update() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return false;
	}//end update() - biere

	@Override
	public boolean delete(Integer pIdBiere) {

		PreparedStatement ps = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, pIdBiere);
			
			int verifSuppr = ps.executeUpdate();
			
			return (verifSuppr == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la suppression de la bière / méthode delete() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally block
		
		return false;
	}//end delete() - biere

	@Override
	public Biere getById(Integer pIdBiere) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, pIdBiere);
			
			rs = ps.executeQuery();
			
			Biere biere = null;
			
			biere = new Biere(rs.getInt(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getInt(4),
							  rs.getInt(5),
							  rs.getBoolean(6),
							  rs.getBytes(7),
							  rs.getDouble(8),
							  rs.getInt(9),
							  rs.getInt(10));
			
			return biere;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la bière / méthode getById() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getById() - biere

	@Override
	public Biere getByNom(String pNomBiere) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setString(1, pNomBiere);
			
			rs = ps.executeQuery();
			
			Biere biere = null;
			
			biere = new Biere(rs.getInt(1),
							  rs.getString(2),
							  rs.getString(3),
							  rs.getInt(4),
							  rs.getInt(5),
							  rs.getBoolean(6),
							  rs.getBytes(7),
							  rs.getDouble(8),
							  rs.getInt(9),
							  rs.getInt(10));
			
			return biere;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la bière / méthode getByNom() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getByNom() - biere

	@Override
	public List<Biere> getAll() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			rs = ps.executeQuery();
			
			Biere biere = null;
			List<Biere> listeBieres = new ArrayList<>();
			
			while (rs.next()) {
				
				biere = new Biere(rs.getInt(1),
								  rs.getString(2),
								  rs.getString(3),
								  rs.getInt(4),
								  rs.getInt(5),
								  rs.getBoolean(6),
								  rs.getBytes(7),
								  rs.getDouble(8),
								  rs.getInt(9),
								  rs.getInt(10));
				
				listeBieres.add(biere);
				
			}//end while
			
			return listeBieres;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des bières / méthode getAll() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getAll() - biere

	@Override
	public List<Biere> getByPays(int idPays) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idPays);
			
			rs = ps.executeQuery();
			
			Biere biere = null;
			List<Biere> listeBieres = new ArrayList<>();
			
			while (rs.next()) {
				
				biere = new Biere(rs.getInt(1),
								  rs.getString(2),
								  rs.getString(3),
								  rs.getInt(4),
								  rs.getInt(5),
								  rs.getBoolean(6),
								  rs.getBytes(7),
								  rs.getDouble(8),
								  rs.getInt(9),
								  rs.getInt(10));
				
				listeBieres.add(biere);
				
			}//end while
			
			return listeBieres;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des bières / méthode getByPays() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getByPays() - biere

	@Override
	public List<Biere> getByType(int idType) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.connection.prepareStatement("");
			
			ps.setInt(1, idType);
			
			rs = ps.executeQuery();
			
			Biere biere = null;
			List<Biere> listeBieres = new ArrayList<>();
			
			while (rs.next()) {
				
				biere = new Biere(rs.getInt(1),
								  rs.getString(2),
								  rs.getString(3),
								  rs.getInt(4),
								  rs.getInt(5),
								  rs.getBoolean(6),
								  rs.getBytes(7),
								  rs.getDouble(8),
								  rs.getInt(9),
								  rs.getInt(10));
				
				listeBieres.add(biere);
				
			}//end while
			
			return listeBieres;
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur lors de la récupération de la liste des bières / méthode getByType() ...");
		
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return null;
	}//end getByType() - biere

}//end class
